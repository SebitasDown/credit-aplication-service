package com.CoopCredit.credit_application_service.application.service;

import com.CoopCredit.credit_application_service.application.port.in.user.RegisterUserUseCase;
import com.CoopCredit.credit_application_service.application.port.in.user.command.RegisterCommand;
import com.CoopCredit.credit_application_service.application.port.out.RoleRepository;
import com.CoopCredit.credit_application_service.application.port.out.UserRepository;
import com.CoopCredit.credit_application_service.domain.model.Role;
import com.CoopCredit.credit_application_service.domain.model.User;
import com.CoopCredit.credit_application_service.infraestructure.config.TransactionalUseCaseExecutor;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RegisterUserService implements RegisterUserUseCase {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TransactionalUseCaseExecutor transactional;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            TransactionalUseCaseExecutor transactional,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.transactional = transactional;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(RegisterCommand command) {
        return transactional.executeInTransaction(() -> {

            // Validar que el usuario no exista
            if (userRepository.existByUsername(command.username())) {
                throw new com.CoopCredit.credit_application_service.domain.exception.BusinessException(
                        "El usuario ya existe");
            }

            // Buscar el rol
            Role role = roleRepository.findByName(command.roleName())
                    .orElseThrow(() -> new com.CoopCredit.credit_application_service.domain.exception.BusinessException(
                            "Rol no encontrado"));

            // Encriptar la contraseña
            String encodedPassword = passwordEncoder.encode(command.password());

            // Crear y guardar el usuario
            User user = new User(null, command.username(), encodedPassword, Set.of(role));
            return userRepository.save(user);
        });
    }
}