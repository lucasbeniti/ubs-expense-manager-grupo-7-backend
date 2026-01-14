package com.empresa.projeto.gestao_ubs.Entity;

import com.empresa.projeto.gestao_ubs.Enums.ExpenseLogAction;
import com.empresa.projeto.gestao_ubs.Enums.ExpenseStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "expense_logs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "action", length = 20)
    private ExpenseLogAction action;

    @Column(name = "comments")
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expense_id")
    private Expense expense;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
