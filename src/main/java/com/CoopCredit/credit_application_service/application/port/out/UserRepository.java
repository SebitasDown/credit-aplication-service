package com.CoopCredit.credit_application_service.application.port.out;

import com.CoopCredit.credit_application_service.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByUsername(String username);
    boolean existByUsername(String username);
}
