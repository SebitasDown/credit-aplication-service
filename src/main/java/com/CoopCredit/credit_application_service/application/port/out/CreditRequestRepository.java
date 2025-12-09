package com.CoopCredit.credit_application_service.application.port.out;

import com.CoopCredit.credit_application_service.domain.model.CreditRequest;

public interface CreditRequestRepository {
    CreditRequest save(CreditRequest request);
}
