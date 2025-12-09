package com.CoopCredit.credit_application_service.infraestructure.out.persistence.adapter;

import com.CoopCredit.credit_application_service.application.port.out.CreditRequestRepository;
import com.CoopCredit.credit_application_service.domain.model.CreditRequest;
import com.CoopCredit.credit_application_service.infraestructure.out.persistence.entity.AffiliateEntity;
import com.CoopCredit.credit_application_service.infraestructure.out.persistence.entity.CreditRequestEntity;
import com.CoopCredit.credit_application_service.infraestructure.out.persistence.jpa.AffiliateJpaRepository;
import com.CoopCredit.credit_application_service.infraestructure.out.persistence.jpa.CreditRequestJpaRepository;
import com.CoopCredit.credit_application_service.infraestructure.out.persistence.mapper.CreditRequestPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditRequestRepositoryAdapter implements CreditRequestRepository {
    private final CreditRequestJpaRepository jpaRepository;
    private final AffiliateJpaRepository affiliateJpaRepository;
    private final CreditRequestPersistenceMapper mapper;

    @Override
    public CreditRequest save(CreditRequest request) {
        AffiliateEntity affiliateEntity = affiliateJpaRepository.findById(request.getAffiliate().getId())
                .orElseThrow(() -> new IllegalArgumentException("Afiliado no encontrado"));

        CreditRequestEntity entity = mapper.toEntity(request, affiliateEntity);
        CreditRequestEntity saved = jpaRepository.save(entity);

        return mapper.toDomain(saved);
    }

    @Override
    public java.util.List<CreditRequest> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public java.util.List<CreditRequest> findByAffiliateDocument(String document) {
        return jpaRepository.findByAffiliateDocument(document).stream()
                .map(mapper::toDomain)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public java.util.List<CreditRequest> findByStatus(
            com.CoopCredit.credit_application_service.domain.model.CreditRequestStatus status) {
        return jpaRepository.findByStatus(status).stream()
                .map(mapper::toDomain)
                .collect(java.util.stream.Collectors.toList());
    }
}
