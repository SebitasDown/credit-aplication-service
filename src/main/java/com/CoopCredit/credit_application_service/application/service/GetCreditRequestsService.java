package com.CoopCredit.credit_application_service.application.service;

import com.CoopCredit.credit_application_service.application.port.in.creditRequest.GetCreditRequestsUseCase;
import com.CoopCredit.credit_application_service.application.port.out.CreditRequestRepository;
import com.CoopCredit.credit_application_service.domain.model.CreditRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCreditRequestsService implements GetCreditRequestsUseCase {

    private final CreditRequestRepository creditRequestRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CreditRequest> getRequests(String username, Collection<String> roles) {
        if (roles.contains("ROLE_ADMIN")) {
            return creditRequestRepository.findAll();
        }

        if (roles.contains("ROLE_ANALISTA")) {
            // Analysts should see all history, not just pending ones
            return creditRequestRepository.findAll();
        }

        if (roles.contains("ROLE_AFILIADO")) {
            return creditRequestRepository.findByAffiliateDocument(username);
        }

        return Collections.emptyList();
    }
}
