package com.CoopCredit.credit_application_service.application.service;

import com.CoopCredit.credit_application_service.application.port.in.creditRequest.GetCreditRequestsUseCase;
import com.CoopCredit.credit_application_service.application.port.out.CreditRequestRepository;
import com.CoopCredit.credit_application_service.domain.model.CreditRequest;
import com.CoopCredit.credit_application_service.domain.model.CreditRequestStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCreditRequestsService implements GetCreditRequestsUseCase {

    private final CreditRequestRepository creditRequestRepository;

    @Override
    public List<CreditRequest> getRequests(String username, Collection<String> roles) {
        if (roles.contains("ROLE_ADMIN")) {
            return creditRequestRepository.findAll();
        }

        if (roles.contains("ROLE_ANALISTA")) {
            return creditRequestRepository.findByStatus(CreditRequestStatus.PENDIENTE);
        }

        if (roles.contains("ROLE_AFILIADO")) {
            return creditRequestRepository.findByAffiliateDocument(username);
        }

        return Collections.emptyList();
    }
}
