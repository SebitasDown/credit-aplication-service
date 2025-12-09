package com.CoopCredit.credit_application_service.application.service;

import com.CoopCredit.credit_application_service.application.port.in.user.AuthenticateUserUseCase;
import com.CoopCredit.credit_application_service.application.port.in.user.command.AuthCommand;
import com.CoopCredit.credit_application_service.application.port.in.user.command.AuthenticationResponse;
import com.CoopCredit.credit_application_service.application.port.out.UserRepository;
import com.CoopCredit.credit_application_service.domain.model.Role;
import com.CoopCredit.credit_application_service.domain.model.User;
import com.CoopCredit.credit_application_service.infraestructure.config.TransactionalUseCaseExecutor;
import com.CoopCredit.credit_application_service.infraestructure.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthenticateUserService implements AuthenticateUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final TransactionalUseCaseExecutor transactional;

    public AuthenticateUserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil,
            TransactionalUseCaseExecutor transactional) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.transactional = transactional;
    }

    @Override
    public AuthenticationResponse authenticate(AuthCommand command) {
        return transactional.executeInTransaction(() -> {
            // Buscar el usuario
            User user = userRepository.findByUsername(command.username())
                    .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

            // Validar la contraseña
            if (!passwordEncoder.matches(command.password(), user.getPassword())) {
                throw new RuntimeException("Credenciales inválidas");
            }

            // Extraer los nombres de roles del usuario
            Set<String> roles = user.getRoles().stream()
                    .map(Role::getName)
                    .collect(Collectors.toSet());

            // Generar el JWT token
            String token = jwtUtil.generateToken(user.getUsername(), roles);

            return new AuthenticationResponse(token);
        });
    }
}
