package com.CoopCredit.credit_application_service.infraestructure.in.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@lombok.NoArgsConstructor
public class CreditRequestResponseDTO {
    private Long requestId;
    private String status;
    private int score;
    private String riskLevel;
    private String detail;
}