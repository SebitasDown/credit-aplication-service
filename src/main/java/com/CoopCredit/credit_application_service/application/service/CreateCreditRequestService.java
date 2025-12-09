package com.CoopCredit.credit_application_service.application.service;

import com.CoopCredit.credit_application_service.application.port.in.creditRequest.CreateCreditRequestUseCase;
import com.CoopCredit.credit_application_service.application.port.out.CreditRequestRepository;
import com.CoopCredit.credit_application_service.domain.model.AffiliateStatus;
import com.CoopCredit.credit_application_service.domain.model.Affiliates;
import com.CoopCredit.credit_application_service.domain.model.CreditRequest;
import com.CoopCredit.credit_application_service.domain.model.CreditRequestStatus;

public class CreateCreditRequestService implements CreateCreditRequestUseCase {

    private final CreditRequestRepository creditRequestRepository;

    public CreateCreditRequestService(CreditRequestRepository creditRequestRepository) {
        this.creditRequestRepository = creditRequestRepository;
    }

    @Override
    public CreditRequest createRequest(Affiliates affiliate, double amount, int term, double proposedRate) {
        if (affiliate.getStatus() != AffiliateStatus.ACTIVO) {
            throw new IllegalArgumentException("El afiliado debe estar ACTIVO para solicitar crédito");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a 0");
        }
        if (term <= 0) {
            throw new IllegalArgumentException("El plazo debe ser mayor a 0");
        }

        CreditRequest request = new CreditRequest(null, affiliate, amount, term, proposedRate);
        request.setStatus(CreditRequestStatus.PENDIENTE);
        return request;
    }
}
