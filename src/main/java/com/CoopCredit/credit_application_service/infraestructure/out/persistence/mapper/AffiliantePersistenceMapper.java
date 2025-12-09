package com.CoopCredit.credit_application_service.infraestructure.out.persistence.mapper;

import com.CoopCredit.credit_application_service.domain.model.Affiliates;
import com.CoopCredit.credit_application_service.infraestructure.out.persistence.entity.AffiliateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AffiliantePersistenceMapper {
    Affiliates toDomain(AffiliateEntity entity);

    AffiliateEntity toEntity(Affiliates domain);
}
