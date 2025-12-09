package com.CoopCredit.credit_application_service.infraestructure.in.web.controller;

import com.CoopCredit.credit_application_service.application.port.in.affiliate.CreateAffiliateUseCase;
import com.CoopCredit.credit_application_service.domain.model.AffiliateStatus;
import com.CoopCredit.credit_application_service.domain.model.Affiliates;
import com.CoopCredit.credit_application_service.infraestructure.in.web.dto.request.AffiliateRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/affiliates")
@RequiredArgsConstructor
@Tag(name = "Affiliates", description = "Operations related to affiliates")
public class AffiliateController {

    private final CreateAffiliateUseCase createAffiliateUseCase;

    @PostMapping
    @Operation(summary = "Create a new affiliate", description = "Creates a new affiliate in the system.", security = @SecurityRequirement(name = "BearerAuth"))
    public ResponseEntity<Affiliates> createAffiliate(@RequestBody AffiliateRequestDTO dto) {
        Affiliates affiliate = new Affiliates(
                null,
                dto.getDocument(),
                dto.getName(),
                dto.getSalary(),
                dto.getJoinDate(),
                AffiliateStatus.valueOf(dto.getStatus()),
                null);

        Affiliates savedAffiliate = createAffiliateUseCase.createAffiliate(affiliate);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAffiliate);
    }
}
