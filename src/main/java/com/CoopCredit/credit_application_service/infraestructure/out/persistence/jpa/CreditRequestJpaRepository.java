package com.CoopCredit.credit_application_service.infraestructure.out.persistence.jpa;

import com.CoopCredit.credit_application_service.infraestructure.out.persistence.entity.CreditRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRequestJpaRepository extends JpaRepository<CreditRequestEntity, Long> {
    java.util.List<CreditRequestEntity> findByAffiliateDocument(String document);

    java.util.List<CreditRequestEntity> findByStatus(
            com.CoopCredit.credit_application_service.domain.model.CreditRequestStatus status);
}
