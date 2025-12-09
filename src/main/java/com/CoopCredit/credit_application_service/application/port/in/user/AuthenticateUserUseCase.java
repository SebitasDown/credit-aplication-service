package com.CoopCredit.credit_application_service.application.port.in.user;

import com.CoopCredit.credit_application_service.application.port.in.user.command.AuthCommand;
import com.CoopCredit.credit_application_service.application.port.in.user.command.AuthenticationResponse;

public interface AuthenticateUserUseCase {
    AuthenticationResponse authenticate(AuthCommand command);
}
