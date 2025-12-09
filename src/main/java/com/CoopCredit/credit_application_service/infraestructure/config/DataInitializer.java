package com.CoopCredit.credit_application_service.infraestructure.config;

import com.CoopCredit.credit_application_service.application.port.out.RoleRepository;
import com.CoopCredit.credit_application_service.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        List<String> roles = List.of("ROLE_AFILIADO", "ROLE_ANALISTA", "ROLE_ADMIN");

        for (String roleName : roles) {
            if (roleRepository.findByName(roleName).isEmpty()) {
                roleRepository.save(new Role(null, roleName));
            }
        }
    }
}
