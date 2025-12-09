package com.CoopCredit.credit_application_service.application.port.in.user.command;

public record RegisterCommand(String username, String password, String roleName) {
}

