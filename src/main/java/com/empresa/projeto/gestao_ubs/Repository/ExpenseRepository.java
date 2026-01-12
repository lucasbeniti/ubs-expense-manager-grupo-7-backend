package com.empresa.projeto.gestao_ubs.Repository;

import com.empresa.projeto.gestao_ubs.Dto.Report.CategoryExpense.CategoryExpenseDto;
import com.empresa.projeto.gestao_ubs.Dto.Report.DepartmentExpense.DepartmentExpenseDto;
import com.empresa.projeto.gestao_ubs.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("""
        SELECT e
        FROM Expense e
        JOIN FETCH e.employee emp
        LEFT JOIN FETCH e.category c
        JOIN FETCH e.currency cur
        WHERE e.date BETWEEN :start AND :end
        AND (:employee_id IS NULL OR emp.id = :employee_id)
        AND (:category_id IS NULL OR c.id = :category_id)  \s
        ORDER BY emp.name, e.date
   \s""")
    List<Expense> findExpensesForEmployeeReport(
            @Param("start") LocalDate start,
            @Param("end") LocalDate end,
            @Param("employee_id") Long employeeId,
            @Param("category_id") Long categoryId
    );

    @Query("""
        SELECT new com.empresa.projeto.gestao_ubs.Dto.Report.CategoryExpense.CategoryExpenseDto(
            c.id,
            c.name,
            MAX(e.amount)
        )
        FROM Expense e
        JOIN e.category c
        WHERE (:category_id IS NULL OR c.id = :category_id)
        GROUP BY c.id, c.name
        ORDER BY MAX(e.amount) DESC
    """)

    List<CategoryExpenseDto> findExpenseByCategory(
            @Param("category_id") Long categoryId
    );

    @Query("""
        SELECT new com.empresa.projeto.gestao_ubs.Dto.Report.DepartmentExpense.DepartmentExpenseDto(
            d.id,
            d.name,
            d.monthlyBudget,
            MAX(e.amount)
        )
        FROM Expense e
        JOIN e.employee emp
        JOIN emp.department d
        WHERE (:department_id IS NULL OR d.id = :department_id)
        GROUP BY d.id, d.name, d.monthlyBudget
        ORDER BY MAX(e.amount) DESC
    """)
    List<DepartmentExpenseDto> findExpenseByDepartment(
            @Param("department_id") Long departmentId
    );

    @Query("""
        SELECT COALESCE(SUM(e.amount), 0)
        FROM Expense e
        WHERE e.category.id = :categoryId
          AND e.createdAt >= :start
          AND e.createdAt < :end
    """)
    BigDecimal sumExpenseByCategory(
            @Param("categoryId") Long categoryId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e " +
            "WHERE e.employee.department.id = :departmentId " +
            "AND e.createdAt BETWEEN :start AND :end")
    BigDecimal sumExpenseByDepartment(@Param("departmentId") Long departmentId,
                                    @Param("start") LocalDateTime start,
                                    @Param("end") LocalDateTime end);


}
