package com.CoopCredit.credit_application_service.infraestructure.in.web.mapper;

import com.CoopCredit.credit_application_service.domain.model.CreditRequest;
import com.CoopCredit.credit_application_service.domain.model.CreditEvaluation;
import com.CoopCredit.credit_application_service.infraestructure.in.web.dto.request.CreditRequestRequestDTO;
import com.CoopCredit.credit_application_service.infraestructure.in.web.dto.response.CreditRequestResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreditRequestMapper {


    default CreditRequest toDomain(CreditRequestRequestDTO dto) {
        return new CreditRequest(
                null,
                null,
                dto.getAmount(),
                dto.getTerm(),
                dto.getProposedRate()
        );
    }


    default CreditRequestResponseDTO toResponseDTO(CreditRequest request) {
        CreditEvaluation evaluation = request.getEvaluation();
        return new CreditRequestResponseDTO(
                request.getId(),
                request.getStatus().toString(),
                evaluation != null ? evaluation.getScore() : 0,
                evaluation != null ? evaluation.getRiskLevel() : null,
                evaluation != null ? evaluation.getCentralDetail() : null
        );
    }
}
