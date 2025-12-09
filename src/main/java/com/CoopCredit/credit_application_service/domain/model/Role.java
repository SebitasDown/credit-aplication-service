package com.CoopCredit.credit_application_service.domain.model;

import java.util.Set;

public class Role {
    private Long id;
    private String name;

    public Role(Long id, String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre del rol es obligatorio");
        }
        this.id = id;
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
}