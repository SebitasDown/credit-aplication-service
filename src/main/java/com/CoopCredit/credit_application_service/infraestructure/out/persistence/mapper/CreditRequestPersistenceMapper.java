package com.CoopCredit.credit_application_service.infraestructure.out.persistence.mapper;

import com.CoopCredit.credit_application_service.domain.model.CreditRequest;
import com.CoopCredit.credit_application_service.domain.model.Affiliates;
import com.CoopCredit.credit_application_service.infraestructure.out.persistence.entity.AffiliateEntity;
import com.CoopCredit.credit_application_service.infraestructure.out.persistence.entity.CreditRequestEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = AffiliantePersistenceMapper.class)
public interface CreditRequestPersistenceMapper {

    @Mappings({
        @Mapping(target = "affiliate", source = "affiliate"),
        @Mapping(target = "id", source = "id")
    })
    CreditRequest toDomain(CreditRequestEntity entity);

    @Mappings({
        @Mapping(target = "affiliate", source = "affiliateEntity"),
        @Mapping(target = "id", source = "domain.id"),
        @Mapping(target = "amount", source = "domain.amount"),
        @Mapping(target = "term", source = "domain.term"),
        @Mapping(target = "proposedRate", source = "domain.proposedRate"),
        @Mapping(target = "requestDate", source = "domain.requestDate"),
        @Mapping(target = "status", source = "domain.status")
    })
    CreditRequestEntity toEntity(CreditRequest domain, AffiliateEntity affiliateEntity);

}
