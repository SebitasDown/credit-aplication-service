package com.CoopCredit.credit_application_service.domain.model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private Long id;
    private String username;
    private String password;
    private Set<Role> roles = new HashSet<>();

    public User(Long id, String username, String password, Set<Role> roles) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("El nombre de usuario es obligatorio");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles != null ? roles : new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        if (role != null) {
            roles.add(role);
        }
    }

    public boolean hasRole(String roleName) {
        return roles.stream().anyMatch(r -> r.getName().equals(roleName));
    }
}