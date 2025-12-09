package com.CoopCredit.credit_application_service.application.port.out;

import com.CoopCredit.credit_application_service.domain.model.CreditRequest;

public interface CreditRequestRepository {
    CreditRequest save(CreditRequest request);

    java.util.List<CreditRequest> findAll();

    java.util.List<CreditRequest> findByAffiliateDocument(String document);

    java.util.List<CreditRequest> findByStatus(
            com.CoopCredit.credit_application_service.domain.model.CreditRequestStatus status);
}
