package com.empresa.projeto.gestao_ubs.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_uuid", nullable = false, unique = true, updatable = false)
    private UUID categoryUuid;

    @NotBlank
    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @NotNull
    @Positive
    @Column(name = "daily_limit", nullable = false, precision = 19, scale = 2)
    private BigDecimal dailyLimit;

    @NotNull
    @Positive
    @Column(name = "monthly_limit", nullable = false, precision = 19, scale = 2)
    private BigDecimal monthlyLimit;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.categoryUuid = UUID.randomUUID();
    }
}

