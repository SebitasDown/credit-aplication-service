package com.CoopCredit.credit_application_service.infraestructure.in.web.mapper;



import com.CoopCredit.credit_application_service.application.port.in.user.command.AuthCommand;
import com.CoopCredit.credit_application_service.application.port.in.user.command.AuthenticationResponse;
import com.CoopCredit.credit_application_service.application.port.in.user.command.RegisterCommand;
import com.CoopCredit.credit_application_service.domain.model.User;
import com.CoopCredit.credit_application_service.infraestructure.in.web.dto.request.AuthRequest;
import com.CoopCredit.credit_application_service.infraestructure.in.web.dto.request.RegisterRequest;
import com.CoopCredit.credit_application_service.infraestructure.in.web.dto.response.AuthResponse;
import com.CoopCredit.credit_application_service.infraestructure.in.web.dto.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    // Request → Command (entrada)
    RegisterCommand toRegisterCommand(RegisterRequest request);

    AuthCommand toAuthCommand(AuthRequest request);

    // Domain → Response (salida)
    UserResponse toUserResponse(User user);

    AuthResponse toAuthResponse(AuthenticationResponse authenticationResponse);
}
