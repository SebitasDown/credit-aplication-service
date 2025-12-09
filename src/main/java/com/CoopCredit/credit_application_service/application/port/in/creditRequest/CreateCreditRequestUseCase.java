package com.CoopCredit.credit_application_service.application.port.in.creditRequest;

import com.CoopCredit.credit_application_service.domain.model.Affiliates;
import com.CoopCredit.credit_application_service.domain.model.CreditRequest;

public interface CreateCreditRequestUseCase {
    CreditRequest createRequest(Affiliates affiliate, double amount, int term, double proposedRate);
}
