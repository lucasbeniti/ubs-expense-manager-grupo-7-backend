package com.empresa.projeto.gestao_ubs.Entity;

import com.empresa.projeto.gestao_ubs.Enums.EmployeeRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "employee_uuid", nullable = false, unique = true, updatable = false)
    private UUID employeeUuid;

    @NotBlank
    @Column(nullable = false, length = 150)
    private String name;

    @NotBlank
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EmployeeRole role;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_department_id", nullable = false)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_manager_id")
    private Employee manager;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.employeeUuid = UUID.randomUUID();
    }
}

