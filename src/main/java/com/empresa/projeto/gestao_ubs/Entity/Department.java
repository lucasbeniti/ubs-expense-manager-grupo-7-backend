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
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "department_uuid", nullable = false, unique = true, updatable = false)
    private UUID departmentUuid;

    @NotBlank
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @NotNull
    @Positive
    @Column(name = "monthly_budget", nullable = false, precision = 19, scale = 2)
    private BigDecimal monthlyBudget;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.departmentUuid = UUID.randomUUID();
    }
}