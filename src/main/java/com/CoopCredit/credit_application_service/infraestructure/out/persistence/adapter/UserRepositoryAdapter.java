package com.CoopCredit.credit_application_service.infraestructure.out.persistence.adapter;

import com.CoopCredit.credit_application_service.application.port.out.UserRepository;
import com.CoopCredit.credit_application_service.domain.model.User;
import com.CoopCredit.credit_application_service.infraestructure.out.persistence.entity.UserEntity;
import com.CoopCredit.credit_application_service.infraestructure.out.persistence.jpa.JpaUserRepository;
import com.CoopCredit.credit_application_service.infraestructure.out.persistence.mapper.UserPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final JpaUserRepository userRepository;
    private final UserPersistenceMapper userMapper;

    @Override
    public User save(User user) {
        UserEntity entity = userMapper.toEntity(user);
        UserEntity saved = userRepository.save(entity);
        return userMapper.toDomain(saved);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toDomain);
    }

    @Override
    public boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
