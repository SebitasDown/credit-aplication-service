package com.CoopCredit.credit_application_service.infraestructure.config;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Component
public class TransactionalUseCaseExecutor {

    @Transactional
    public <T> T executeInTransaction(Supplier<T> supplier) {
        return supplier.get();
    }

    @Transactional
    public void executeInTransaction(Runnable runnable) {
        runnable.run();
    }
}
