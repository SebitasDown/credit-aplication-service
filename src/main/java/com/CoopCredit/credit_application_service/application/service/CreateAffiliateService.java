package com.CoopCredit.credit_application_service.application.service;

import com.CoopCredit.credit_application_service.application.port.in.affiliate.CreateAffiliateUseCase;
import com.CoopCredit.credit_application_service.application.port.out.AffiliateRepository;
import com.CoopCredit.credit_application_service.domain.model.Affiliates;
import com.CoopCredit.credit_application_service.infraestructure.config.TransactionalUseCaseExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAffiliateService implements CreateAffiliateUseCase {

    private final AffiliateRepository affiliateRepository;
    private final TransactionalUseCaseExecutor transactionalUseCaseExecutor;

    @Override
    public Affiliates createAffiliate(Affiliates affiliate) {
        return transactionalUseCaseExecutor.executeInTransaction(() -> {
            // Here you could add business rules like checking if document already exists
            return affiliateRepository.save(affiliate);
        });
    }
}
