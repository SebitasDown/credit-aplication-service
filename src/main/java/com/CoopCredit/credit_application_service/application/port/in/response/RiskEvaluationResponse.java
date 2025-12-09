package com.CoopCredit.credit_application_service.application.port.in.response;

public class RiskEvaluationResponse {
    private int score;
    private String riskLevel;
    private String detail;

    public RiskEvaluationResponse() {
    }

    public RiskEvaluationResponse(int score, String riskLevel, String detail) {
        this.score = score;
        this.riskLevel = riskLevel;
        this.detail = detail;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}