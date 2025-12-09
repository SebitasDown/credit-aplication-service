package com.CoopCredit.credit_application_service.application.port.out;

import com.CoopCredit.credit_application_service.application.port.in.response.RiskEvaluationResponse;

public interface RiskCentralClient {
    RiskEvaluationResponse evaluateRisk(String document, double amount, int term);
}
