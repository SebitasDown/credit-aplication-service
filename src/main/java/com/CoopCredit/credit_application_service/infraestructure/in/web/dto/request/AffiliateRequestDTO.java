package com.CoopCredit.credit_application_service.infraestructure.in.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AffiliateRequestDTO {
    private String document;
    private String name;
    private double salary;
    private LocalDate joinDate;
    private String status; // ACTIVO, INACTIVO
}
