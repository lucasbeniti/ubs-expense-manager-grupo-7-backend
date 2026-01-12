package com.empresa.projeto.gestao_ubs.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
    private Long expenseLogId;

    @Column(name = "action")
    private Long action;

    @Column(name = "comments")
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_expense_id")
    private Expense expense;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_employee_id")
    private Employee employee;

    @Column(name="created_at")
    private LocalDateTime createdAt;

}
