package com.empresa.projeto.gestao_ubs.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currency_rate_id")
    private Long currencyRateId;

    @Column(name = "currency_rate_uuid", nullable = false, unique = true, updatable = false)
    private UUID currencyRateUuid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_currency_id", nullable = false)
    private Currency currency;

    @NotNull
    @Column(nullable = false, precision = 19, scale = 6)
    private BigDecimal rate;

    @NotNull
    @Column(name = "valid_date", nullable = false)
    private LocalDate validDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.currencyRateUuid = UUID.randomUUID();
    }
}

