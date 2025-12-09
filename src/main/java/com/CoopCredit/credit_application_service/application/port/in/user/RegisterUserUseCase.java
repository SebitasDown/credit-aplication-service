package com.CoopCredit.credit_application_service.application.port.in.user;

import com.CoopCredit.credit_application_service.application.port.in.user.command.RegisterCommand;
import com.CoopCredit.credit_application_service.domain.model.User;

public interface RegisterUserUseCase {
    User createUser(RegisterCommand registreCommand);
}
