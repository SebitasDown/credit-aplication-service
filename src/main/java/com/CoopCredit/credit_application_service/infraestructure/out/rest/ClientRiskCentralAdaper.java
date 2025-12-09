package com.CoopCredit.credit_application_service.infraestructure.out.rest;

import com.CoopCredit.credit_application_service.application.port.in.response.RiskEvaluationResponse;
import com.CoopCredit.credit_application_service.application.port.out.RiskCentralClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ClientRiskCentralAdaper implements RiskCentralClient {

    private final WebClient riskCentralWebClient;

    @Override
    public RiskEvaluationResponse evaluateRisk(String document, double amount, int term) {
        RiskEvaluationResponse response = riskCentralWebClient.post()
                .uri("/api/risk/evaluate")
                .bodyValue(Map.of(
                        "document", document,
                        "amount", amount,
                        "term", term
                ))
                .retrieve()
                .bodyToMono(RiskEvaluationResponse.class)
                .block();

        if (response == null) {
            throw new RuntimeException("No se recibió respuesta del servicio de riesgo");
        }

        return response;
    }
}
