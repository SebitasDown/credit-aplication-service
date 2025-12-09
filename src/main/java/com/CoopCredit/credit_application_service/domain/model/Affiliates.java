package com.CoopCredit.credit_application_service.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Affiliates {
    private Long id;
    private String document;
    private String name;
    private double salary;
    private LocalDate joinDate;
    private AffiliateStatus status;
    private List<CreditRequest> creditRequests = new ArrayList<>();

    public Affiliates(Long id, String document, String name, double salary, LocalDate joinDate, AffiliateStatus status, List<CreditRequest> creditRequests) {
        this.id = id;
        this.document = document;
        this.name = name;
        this.salary = salary;
        this.joinDate = joinDate;
        this.status = status;
        this.creditRequests = creditRequests;
    }

    public boolean isActive() {
        return this.status == AffiliateStatus.ACTIVO;
    }

    public boolean hasValidSalary() {
        return this.salary > 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public AffiliateStatus getStatus() {
        return status;
    }

    public void setStatus(AffiliateStatus status) {
        this.status = status;
    }

    public List<CreditRequest> getCreditRequests() {
        return creditRequests;
    }

    public void setCreditRequests(List<CreditRequest> creditRequests) {
        this.creditRequests = creditRequests;
    }
}
