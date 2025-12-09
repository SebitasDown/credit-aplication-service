package com.CoopCredit.credit_application_service.application.port.in.affiliate;

import com.CoopCredit.credit_application_service.domain.model.Affiliates;

public interface CreateAffiliateUseCase {
    Affiliates createAffiliate(Affiliates affiliate);
}
