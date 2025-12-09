package com.CoopCredit.credit_application_service.infraestructure.out.persistence.adapter;

import com.CoopCredit.credit_application_service.application.port.out.AffiliateRepository;
import com.CoopCredit.credit_application_service.domain.model.Affiliates;
import com.CoopCredit.credit_application_service.infraestructure.out.persistence.jpa.AffiliateJpaRepository;
import com.CoopCredit.credit_application_service.infraestructure.out.persistence.mapper.AffiliantePersistenceMapper; // sic
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AffiliateRepositoryAdapter implements AffiliateRepository {

    private final AffiliateJpaRepository affiliateJpaRepository;
    private final AffiliantePersistenceMapper mapper;

    @Override
    public Optional<Affiliates> findById(Long id) {
        return affiliateJpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Affiliates save(Affiliates affiliate) {
        com.CoopCredit.credit_application_service.infraestructure.out.persistence.entity.AffiliateEntity entity = mapper
                .toEntity(affiliate);
        com.CoopCredit.credit_application_service.infraestructure.out.persistence.entity.AffiliateEntity savedEntity = affiliateJpaRepository
                .save(entity);
        return mapper.toDomain(savedEntity);
    }
}
