package com.CoopCredit.credit_application_service.infraestructure.out.persistence.mapper;

import com.CoopCredit.credit_application_service.domain.model.Affiliates;
import com.CoopCredit.credit_application_service.infraestructure.out.persistence.entity.AffiliateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AffiliantePersistenceMapper {
    @Mapping(target = "creditRequests", ignore = true)
    Affiliates toDomain(AffiliateEntity entity);

    @Mapping(target = "creditRequests", ignore = true)
    AffiliateEntity toEntity(Affiliates domain);
}
