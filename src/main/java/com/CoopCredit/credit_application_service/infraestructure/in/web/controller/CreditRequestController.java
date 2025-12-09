package com.CoopCredit.credit_application_service.infraestructure.in.web.controller;

import com.CoopCredit.credit_application_service.application.port.in.creditRequest.CreateCreditRequestUseCase;
import com.CoopCredit.credit_application_service.domain.model.CreditRequest;
import com.CoopCredit.credit_application_service.infraestructure.in.web.dto.request.CreditRequestRequestDTO;
import com.CoopCredit.credit_application_service.infraestructure.in.web.dto.response.CreditRequestResponseDTO;
import com.CoopCredit.credit_application_service.infraestructure.in.web.mapper.CreditRequestMapper;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/credit-requests")
@RequiredArgsConstructor
@Tag(name = "Credit Requests", description = "Operations related to credit requests")
public class CreditRequestController {

    private final CreateCreditRequestUseCase createCreditRequestUseCase;
    private final com.CoopCredit.credit_application_service.application.port.in.creditRequest.GetCreditRequestsUseCase getCreditRequestsUseCase;
    private final CreditRequestMapper mapper;
    private final com.CoopCredit.credit_application_service.application.port.out.AffiliateRepository affiliateRepository;

    @PostMapping
    @Operation(summary = "Create a new credit request", description = "Allows an affiliate to create a new credit request. Validates the affiliate's status and applies business rules.", security = @SecurityRequirement(name = "BearerAuth"))
    public ResponseEntity<CreditRequestResponseDTO> createCreditRequest(@RequestBody CreditRequestRequestDTO dto) {
        CreditRequest domainRequest = mapper.toDomain(dto);

        com.CoopCredit.credit_application_service.domain.model.Affiliates affiliate = affiliateRepository
                .findById(dto.getAffiliateId())
                .orElseThrow(
                        () -> new com.CoopCredit.credit_application_service.domain.exception.ResourceNotFoundException(
                                "Affiliate not found with id: " + dto.getAffiliateId()));
        domainRequest.setAffiliate(affiliate);

        CreditRequest savedRequest = createCreditRequestUseCase.createRequest(domainRequest);
        CreditRequestResponseDTO responseDTO = mapper.toResponseDTO(savedRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    @Operation(summary = "Get credit requests", description = "Retrieves credit requests based on user role.", security = @SecurityRequirement(name = "BearerAuth"))
    public ResponseEntity<java.util.List<CreditRequestResponseDTO>> getCreditRequests(
            org.springframework.security.core.Authentication authentication) {
        String username = authentication.getName();
        java.util.List<String> roles = authentication.getAuthorities().stream()
                .map(org.springframework.security.core.GrantedAuthority::getAuthority)
                .collect(java.util.stream.Collectors.toList());

        java.util.List<CreditRequest> requests = getCreditRequestsUseCase.getRequests(username, roles);

        java.util.List<CreditRequestResponseDTO> response = requests.stream()
                .map(mapper::toResponseDTO)
                .collect(java.util.stream.Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
