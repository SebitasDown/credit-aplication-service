package com.CoopCredit.credit_application_service.infraestructure.out.persistence.adapter;

import com.CoopCredit.credit_application_service.application.port.out.RoleRepository;
import com.CoopCredit.credit_application_service.domain.model.Role;
import com.CoopCredit.credit_application_service.infraestructure.out.persistence.jpa.JpaRoleRepository;
import com.CoopCredit.credit_application_service.infraestructure.out.persistence.mapper.RolePersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoleRepositoryAdapter implements RoleRepository {

    private final JpaRoleRepository roleRepository;
    private final RolePersistenceMapper roleMapper;

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name)
                .map(roleMapper::toDomain);
    }

    @Override
    public Role save(Role role) {
        var entity = roleMapper.toEntity(role);
        var saved = roleRepository.save(entity);
        return roleMapper.toDomain(saved);
    }
}