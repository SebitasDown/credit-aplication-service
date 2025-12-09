package com.CoopCredit.credit_application_service.application.port.out;

import com.CoopCredit.credit_application_service.domain.model.Affiliates;
import java.util.Optional;

public interface AffiliateRepository {
    Optional<Affiliates> findById(Long id);

    Affiliates save(Affiliates affiliate);
}
