package com.CoopCredit.credit_application_service.application.service;

import com.CoopCredit.credit_application_service.application.port.in.creditRequest.CreateCreditRequestUseCase;
import com.CoopCredit.credit_application_service.application.port.in.response.RiskEvaluationResponse;
import com.CoopCredit.credit_application_service.application.port.out.CreditRequestRepository;
import com.CoopCredit.credit_application_service.application.port.out.RiskCentralClient;
import com.CoopCredit.credit_application_service.domain.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CreateCreditRequestService implements CreateCreditRequestUseCase {

    private final CreditRequestRepository creditRequestRepository;
    private final RiskCentralClient riskCentralClient;
    private final com.CoopCredit.credit_application_service.infraestructure.config.TransactionalUseCaseExecutor transactionalUseCaseExecutor;

    public CreateCreditRequestService(CreditRequestRepository creditRequestRepository,
            RiskCentralClient riskCentralClient,
            com.CoopCredit.credit_application_service.infraestructure.config.TransactionalUseCaseExecutor transactionalUseCaseExecutor) {
        this.creditRequestRepository = creditRequestRepository;
        this.riskCentralClient = riskCentralClient;
        this.transactionalUseCaseExecutor = transactionalUseCaseExecutor;
    }

    @Override
    public CreditRequest createRequest(CreditRequest request) {
        return transactionalUseCaseExecutor.executeInTransaction(() -> {
            // Validaciones basicas
            if (request.getAffiliate().getStatus() != AffiliateStatus.ACTIVO) {
                throw new com.CoopCredit.credit_application_service.domain.exception.BusinessException(
                        "El afiliado debe estar ACTIVO para solicitar crédito");
            }
            if (request.getAmount() <= 0) {
                throw new com.CoopCredit.credit_application_service.domain.exception.BusinessException(
                        "El monto debe ser mayor a 0");
            }
            if (request.getTerm() <= 0) {
                throw new com.CoopCredit.credit_application_service.domain.exception.BusinessException(
                        "El plazo debe ser mayor a 0");
            }

            request.setStatus(CreditRequestStatus.PENDIENTE);

            applyInternalPolicies(request);

            RiskEvaluationResponse riskResponse = riskCentralClient.evaluateRisk(
                    request.getAffiliate().getDocument(),
                    request.getAmount(),
                    request.getTerm());

            evaluateCreditRequest(request, riskResponse);

            return creditRequestRepository.save(request);
        });
    }

    private void applyInternalPolicies(CreditRequest request) {
        boolean quotaIncome = request.getAmount() / request.getTerm() <= request.getAffiliate().getSalary() * 0.3;
        boolean maxAmount = request.getAmount() <= request.getAffiliate().getSalary() * 10;
        boolean minSeniority = request.getAffiliate().getJoinDate().plusMonths(6).isBefore(LocalDate.now());

        CreditEvaluation evaluation = new CreditEvaluation(
                0, null, null,
                quotaIncome,
                maxAmount,
                minSeniority,
                false,
                null);

        request.setEvaluation(evaluation);
    }

    private void evaluateCreditRequest(CreditRequest request, RiskEvaluationResponse riskResponse) {
        CreditEvaluation eval = request.getEvaluation();

        boolean approved = eval.isQuotaIncomeRatioPassed()
                && eval.isMaxAmountPassed()
                && eval.isMinSeniorityPassed()
                && !"ALTO".equals(riskResponse.getRiskLevel());

        String rejectionReason = approved ? null
                : generateRejectionReason(
                        eval.isQuotaIncomeRatioPassed(),
                        eval.isMaxAmountPassed(),
                        eval.isMinSeniorityPassed(),
                        riskResponse);

        CreditEvaluation finalEval = new CreditEvaluation(
                riskResponse.getScore(),
                riskResponse.getRiskLevel(),
                riskResponse.getDetail(),
                eval.isQuotaIncomeRatioPassed(),
                eval.isMaxAmountPassed(),
                eval.isMinSeniorityPassed(),
                approved,
                rejectionReason);

        request.updateStatusFromEvaluation(finalEval);
    }

    private String generateRejectionReason(boolean quota, boolean maxAmount, boolean minSeniority,
            RiskEvaluationResponse risk) {
        StringBuilder reason = new StringBuilder();
        if (!quota)
            reason.append("Cuota mensual excede 30% del salario. ");
        if (!maxAmount)
            reason.append("Monto solicitado supera el máximo permitido. ");
        if (!minSeniority)
            reason.append("Antigüedad mínima de 6 meses no cumplida. ");
        if ("ALTO".equals(risk.getRiskLevel()))
            reason.append("Nivel de riesgo ALTO. ");
        return reason.toString().trim();
    }
}
