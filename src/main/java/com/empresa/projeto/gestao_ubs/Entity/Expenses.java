package com.empresa.projeto.gestao_ubs.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="expenses")
public class Expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expense_id;

    @Column(name= "description")
    private String description;

    @Column(name="date")
    private LocalDate date;

    @Column(name="receipt_url")
    private String receipt_url;

    @Column(name="status")
    private String status;

    @Column(name="amount")
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_currency_id", nullable = false)
    private Currency currency;

    @Column(name="exchange_rate_snapshot")
    private Double exchange_rate_snapshot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_category_id")
    private Category category;

    @Column(name="created_at", nullable = true)
    private LocalDateTime created_at;

    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }
}
