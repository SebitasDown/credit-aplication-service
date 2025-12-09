package com.CoopCredit.credit_application_service.infraestructure.in.web.controller;

import com.CoopCredit.credit_application_service.application.port.in.user.AuthenticateUserUseCase;
import com.CoopCredit.credit_application_service.application.port.in.user.RegisterUserUseCase;
import com.CoopCredit.credit_application_service.application.port.in.user.command.AuthCommand;
import com.CoopCredit.credit_application_service.application.port.in.user.command.AuthenticationResponse;
import com.CoopCredit.credit_application_service.application.port.in.user.command.RegisterCommand;
import com.CoopCredit.credit_application_service.domain.model.User;
import com.CoopCredit.credit_application_service.infraestructure.in.web.dto.request.AuthRequest;
import com.CoopCredit.credit_application_service.infraestructure.in.web.dto.request.RegisterRequest;
import com.CoopCredit.credit_application_service.infraestructure.in.web.dto.response.AuthResponse;
import com.CoopCredit.credit_application_service.infraestructure.in.web.dto.response.UserResponse;
import com.CoopCredit.credit_application_service.infraestructure.in.web.mapper.AuthMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Operations related to user authentication and registration")
public class AuthController {

    private final RegisterUserUseCase createUserUseCase;
    private final AuthenticateUserUseCase authenticateUseCase;
    private final AuthMapper authMapper;

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Creates a new user account with the specified role.")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request) {
        RegisterCommand command = authMapper.toRegisterCommand(request);
        User user = createUserUseCase.createUser(command);
        return ResponseEntity.ok(authMapper.toUserResponse(user));
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate user", description = "Authenticates a user and returns a JWT token.")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthCommand command = authMapper.toAuthCommand(request);
        AuthenticationResponse authResponse = authenticateUseCase.authenticate(command);
        return ResponseEntity.ok(authMapper.toAuthResponse(authResponse));
    }
}
