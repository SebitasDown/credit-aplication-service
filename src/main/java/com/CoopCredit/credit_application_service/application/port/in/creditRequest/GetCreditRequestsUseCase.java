package com.CoopCredit.credit_application_service.application.port.in.creditRequest;

import com.CoopCredit.credit_application_service.domain.model.CreditRequest;
import java.util.Collection;
import java.util.List;

public interface GetCreditRequestsUseCase {
    List<CreditRequest> getRequests(String username, Collection<String> roles);
}
