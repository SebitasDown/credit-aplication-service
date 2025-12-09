package com.CoopCredit.credit_application_service.domain.exception;

public class CreditRequestNotFoundException extends ResourceNotFoundException {
    public CreditRequestNotFoundException(Long id) {
        super("Solicitud de crédito no encontrada con id: " + id);
    }
}
