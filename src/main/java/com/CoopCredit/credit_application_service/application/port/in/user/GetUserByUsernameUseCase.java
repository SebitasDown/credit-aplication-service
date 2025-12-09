package com.CoopCredit.credit_application_service.application.port.in.user;

import com.CoopCredit.credit_application_service.domain.model.User;

import java.util.Optional;

public interface GetUserByUsernameUseCase {
    Optional<User> getUserByUsername(String username);
}
