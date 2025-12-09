package com.CoopCredit.credit_application_service.infraestructure.out.persistence.jpa;

import com.CoopCredit.credit_application_service.infraestructure.out.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaRoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String rolename);
}