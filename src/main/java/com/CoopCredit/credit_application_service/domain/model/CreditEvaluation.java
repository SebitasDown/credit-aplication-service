package com.CoopCredit.credit_application_service.domain.model;

import java.time.LocalDateTime;

public class CreditEvaluation {

    private int score;
    private String riskLevel;
    private String centralDetail;

    private boolean quotaIncomeRatioPassed;
    private boolean maxAmountPassed;
    private boolean minSeniorityPassed;

    private boolean approved;
    private String rejectionReason;
    private LocalDateTime evaluationDate;

    public CreditEvaluation(int score, String riskLevel, String centralDetail,
                            boolean quotaIncomeRatioPassed, boolean maxAmountPassed,
                            boolean minSeniorityPassed, boolean approved,
                            String rejectionReason) {
        this.score = score;
        this.riskLevel = riskLevel;
        this.centralDetail = centralDetail;
        this.quotaIncomeRatioPassed = quotaIncomeRatioPassed;
        this.maxAmountPassed = maxAmountPassed;
        this.minSeniorityPassed = minSeniorityPassed;
        this.approved = approved;
        this.rejectionReason = rejectionReason;
        this.evaluationDate = LocalDateTime.now();
    }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }

    public String getCentralDetail() { return centralDetail; }
    public void setCentralDetail(String centralDetail) { this.centralDetail = centralDetail; }

    public boolean isQuotaIncomeRatioPassed() { return quotaIncomeRatioPassed; }
    public void setQuotaIncomeRatioPassed(boolean quotaIncomeRatioPassed) { this.quotaIncomeRatioPassed = quotaIncomeRatioPassed; }

    public boolean isMaxAmountPassed() { return maxAmountPassed; }
    public void setMaxAmountPassed(boolean maxAmountPassed) { this.maxAmountPassed = maxAmountPassed; }

    public boolean isMinSeniorityPassed() { return minSeniorityPassed; }
    public void setMinSeniorityPassed(boolean minSeniorityPassed) { this.minSeniorityPassed = minSeniorityPassed; }

    public boolean isApproved() { return approved; }
    public void setApproved(boolean approved) { this.approved = approved; }

    public String getRejectionReason() { return rejectionReason; }
    public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }

    public LocalDateTime getEvaluationDate() { return evaluationDate; }
    public void setEvaluationDate(LocalDateTime evaluationDate) { this.evaluationDate = evaluationDate; }
}
