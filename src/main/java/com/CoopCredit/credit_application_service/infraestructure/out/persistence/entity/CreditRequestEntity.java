package com.CoopCredit.credit_application_service.infraestructure.out.persistence.entity;


import com.CoopCredit.credit_application_service.domain.model.CreditRequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "credit_requests")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "affiliate_id", nullable = false)
    private AffiliateEntity affiliate;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private int term;

    @Column(nullable = false)
    private double proposedRate;

    @Column(nullable = false)
    private LocalDate requestDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CreditRequestStatus status;
}