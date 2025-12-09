package com.CoopCredit.credit_application_service.infraestructure.in.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreditRequestRequestDTO {
    private Long affiliateId;
    private double amount;
    private int term;
    private double proposedRate;
}