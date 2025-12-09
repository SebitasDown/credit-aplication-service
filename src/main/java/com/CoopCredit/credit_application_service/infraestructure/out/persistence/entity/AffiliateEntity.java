package com.CoopCredit.credit_application_service.infraestructure.out.persistence.entity;

import com.CoopCredit.credit_application_service.domain.model.AffiliateStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "affiliates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AffiliateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String document;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double salary;

    @Column(name = "join_date", nullable = false)
    private LocalDate joinDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AffiliateStatus status;

    @OneToMany(mappedBy = "affiliate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CreditRequestEntity> creditRequests = new ArrayList<>();
}
