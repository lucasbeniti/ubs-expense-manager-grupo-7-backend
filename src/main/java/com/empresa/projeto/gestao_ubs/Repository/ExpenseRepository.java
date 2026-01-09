package com.empresa.projeto.gestao_ubs.Repository;

import com.empresa.projeto.gestao_ubs.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("""
        SELECT e
        FROM Expense e
        JOIN FETCH e.employee emp
        LEFT JOIN FETCH e.category c
        JOIN FETCH e.currency cur
        WHERE e.date BETWEEN :start AND :end
        AND (:employeeId IS NULL OR emp.employee_id = :employeeId)
        AND (:categoryId IS NULL OR c.category_id = :categoryId)   
        ORDER BY emp.name, e.date
    """)
    List<Expense> findExpensesForEmployeeReport(
            @Param("start") LocalDate start,
            @Param("end") LocalDate end,
            @Param("employeeId") Long employeeId,
            @Param("categoryId") Long categoryId
    );
}
