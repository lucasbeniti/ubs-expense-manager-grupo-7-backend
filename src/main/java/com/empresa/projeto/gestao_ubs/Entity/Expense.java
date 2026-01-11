package com.empresa.projeto.gestao_ubs.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "expenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id")
    private Long expenseId;

    @Column(name = "expense_uuid", nullable = false, unique = true, updatable = false)
    private UUID expenseUuid;

    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "receipt_url", length = 500)
    private String receiptUrl;

    @NotBlank
    @Column(nullable = false, length = 30)
    private String status;

    @NotNull
    @Positive
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_currency_id", nullable = false)
    private Currency currency;

    @NotNull
    @Positive
    @Column(name = "exchange_rate_snapshot", nullable = false, precision = 19, scale = 6)
    private BigDecimal exchangeRateSnapshot;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_category_id", nullable = false)
    private Category category;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.expenseUuid = UUID.randomUUID();
        this.status = "pending";
    }
}

