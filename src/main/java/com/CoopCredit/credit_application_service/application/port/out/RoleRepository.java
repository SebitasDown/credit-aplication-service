package com.CoopCredit.credit_application_service.application.port.out;

import com.CoopCredit.credit_application_service.domain.model.Role;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByName(String name);

    Role save(Role role);
}
