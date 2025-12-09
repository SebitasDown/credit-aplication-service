package com.CoopCredit.credit_application_service.infraestructure.out.persistence.jpa;

import com.CoopCredit.credit_application_service.infraestructure.out.persistence.entity.AffiliateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AffiliateJpaRepository extends JpaRepository<AffiliateEntity, Long> {
    AffiliateEntity findByDocument(String document);
}
