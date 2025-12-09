package com.CoopCredit.credit_application_service.domain.model;

import java.time.LocalDate;

public class CreditRequest {

    private Long id;
    private Affiliates affiliate;
    private double amount;
    private int term;
    private double proposedRate;
    private LocalDate requestDate;
    private CreditRequestStatus status;
    private CreditEvaluation evaluation;

    public CreditRequest(Long id, Affiliates affiliate, double amount, int term, double proposedRate) {
        this.id = id;
        this.affiliate = affiliate;
        this.amount = amount;
        this.term = term;
        this.proposedRate = proposedRate;
        this.requestDate = LocalDate.now();
        this.status = CreditRequestStatus.PENDIENTE;
    }

    public void updateStatusFromEvaluation(CreditEvaluation evaluation) {
        this.evaluation = evaluation;
        this.status = evaluation.isApproved() ? CreditRequestStatus.APROBADO : CreditRequestStatus.RECHAZADO;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Affiliates getAffiliate() { return affiliate; }
    public void setAffiliate(Affiliates affiliate) { this.affiliate = affiliate; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public int getTerm() { return term; }
    public void setTerm(int term) { this.term = term; }

    public double getProposedRate() { return proposedRate; }
    public void setProposedRate(double proposedRate) { this.proposedRate = proposedRate; }

    public LocalDate getRequestDate() { return requestDate; }
    public void setRequestDate(LocalDate requestDate) { this.requestDate = requestDate; }

    public CreditRequestStatus getStatus() { return status; }
    public void setStatus(CreditRequestStatus status) { this.status = status; }

    public CreditEvaluation getEvaluation() { return evaluation; }
    public void setEvaluation(CreditEvaluation evaluation) { this.evaluation = evaluation; }
}
