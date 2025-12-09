package com.CoopCredit.credit_application_service.infraestructure.out.persistence.mapper;

import com.CoopCredit.credit_application_service.domain.model.Role;
import com.CoopCredit.credit_application_service.infraestructure.out.persistence.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolePersistenceMapper {
    RoleEntity toEntity(Role role);

    Role toDomain(RoleEntity entity);
}