package com.CoopCredit.credit_application_service.infraestructure.out.persistence.mapper;

import com.CoopCredit.credit_application_service.domain.model.CreditRequest;
import com.CoopCredit.credit_application_service.domain.model.Affiliates;
import com.CoopCredit.credit_application_service.infraestructure.out.persistence.entity.AffiliateEntity;
import com.CoopCredit.credit_application_service.infraestructure.out.persistence.entity.CreditRequestEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = AffiliantePersistenceMapper.class)
public interface CreditRequestPersistenceMapper {

    @Mappings({
            @Mapping(target = "affiliate", source = "affiliate"),
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "evaluation", expression = "java(toEvaluation(entity))")
    })
    CreditRequest toDomain(CreditRequestEntity entity);

    @Mappings({
            @Mapping(target = "affiliate", source = "affiliateEntity"),
            @Mapping(target = "id", source = "domain.id"),
            @Mapping(target = "amount", source = "domain.amount"),
            @Mapping(target = "term", source = "domain.term"),
            @Mapping(target = "proposedRate", source = "domain.proposedRate"),
            @Mapping(target = "requestDate", source = "domain.requestDate"),
            @Mapping(target = "status", source = "domain.status"),
            @Mapping(target = "score", source = "domain.evaluation.score"),
            @Mapping(target = "riskLevel", source = "domain.evaluation.riskLevel"),
            @Mapping(target = "centralDetail", source = "domain.evaluation.centralDetail"),
            @Mapping(target = "quotaIncomeRatioPassed", source = "domain.evaluation.quotaIncomeRatioPassed"),
            @Mapping(target = "maxAmountPassed", source = "domain.evaluation.maxAmountPassed"),
            @Mapping(target = "minSeniorityPassed", source = "domain.evaluation.minSeniorityPassed"),
            @Mapping(target = "approved", source = "domain.evaluation.approved"),
            @Mapping(target = "rejectionReason", source = "domain.evaluation.rejectionReason"),
            @Mapping(target = "evaluationDate", source = "domain.evaluation.evaluationDate")
    })
    CreditRequestEntity toEntity(CreditRequest domain, AffiliateEntity affiliateEntity);

    default com.CoopCredit.credit_application_service.domain.model.CreditEvaluation toEvaluation(
            CreditRequestEntity entity) {
        if (entity == null || entity.getEvaluationDate() == null) {
            return null;
        }
        com.CoopCredit.credit_application_service.domain.model.CreditEvaluation eval = new com.CoopCredit.credit_application_service.domain.model.CreditEvaluation(
                entity.getScore(),
                entity.getRiskLevel(),
                entity.getCentralDetail(),
                entity.isQuotaIncomeRatioPassed(),
                entity.isMaxAmountPassed(),
                entity.isMinSeniorityPassed(),
                entity.isApproved(),
                entity.getRejectionReason());
        eval.setEvaluationDate(entity.getEvaluationDate());
        return eval;
    }

}
