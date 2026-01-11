package com.empresa.projeto.gestao_ubs.Repository;

import com.empresa.projeto.gestao_ubs.Dto.Report.CategoryExpense.CategoryExpenseDto;
import com.empresa.projeto.gestao_ubs.Dto.Report.DepartmentExpense.DepartmentExpenseDto;
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
        AND (:employee_id IS NULL OR emp.employee_id = :employee_id)
        AND (:category_id IS NULL OR c.category_id = :category_id)   
        ORDER BY emp.name, e.date
    """)
    List<Expense> findExpensesForEmployeeReport(
            @Param("start") LocalDate start,
            @Param("end") LocalDate end,
            @Param("employee_id") Long employee_id,
            @Param("category_id") Long category_id
    );

    @Query("""
        SELECT new com.empresa.projeto.gestao_ubs.Dto.Report.CategoryExpense.CategoryExpenseDto(
            c.category_id,
            c.name,
            MAX(e.amount)
        )
        FROM Expense e
        JOIN e.category c
        WHERE (:category_id IS NULL OR e.category.category_id = :category_id)
        GROUP BY c.category_id, c.name
        ORDER BY MAX(e.amount) DESC
    """)

    List<CategoryExpenseDto> findExpenseByCategory(
            @Param("category_id") Long category_id
    );

    @Query("""
        SELECT new com.empresa.projeto.gestao_ubs.Dto.Report.DepartmentExpense.DepartmentExpenseDto(
            d.department_id,
            d.name,
            d.monthly_budget,
            MAX(e.amount)
        )
        FROM Expense e
        JOIN e.employee emp
        JOIN emp.department d
        WHERE (:department_id IS NULL OR d.department_id = :department_id)
        GROUP BY d.department_id, d.name, d.monthly_budget
        ORDER BY MAX(e.amount) DESC
    """)
    List<DepartmentExpenseDto> findExpenseByDepartment(
            @Param("department_id") Long department_id
    );
}
