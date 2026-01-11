package com.empresa.projeto.gestao_ubs.Service.Rules;

import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertCreateDto;
import com.empresa.projeto.gestao_ubs.Entity.Expense;
import com.empresa.projeto.gestao_ubs.Repository.ExpenseRepository;
import com.empresa.projeto.gestao_ubs.Service.ExpenseRule;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Optional;

@Component
public class DepartmentExpenseRule implements ExpenseRule {

    private final ExpenseRepository expenseRepository;

    public DepartmentExpenseRule(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Optional<AlertCreateDto> check(Expense expense) {
        if (expense.getEmployee() == null || expense.getEmployee().getDepartment() == null) {
            return Optional.empty();
        }

        BigDecimal departmentMonthlyBudget = expense.getEmployee().getDepartment().getMonthly_budget();
        if (departmentMonthlyBudget == null) return Optional.empty();

        YearMonth currentMonth = YearMonth.now();
        LocalDateTime startOfMonth = currentMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = currentMonth.atEndOfMonth().atTime(23, 59, 59);
        BigDecimal totalThisMonth = expenseRepository.sumExpenseByDepartment(
                expense.getEmployee().getDepartment().getDepartment_id(),
                startOfMonth,
                endOfMonth
        );

        BigDecimal totalWithNewExpense = totalThisMonth.add(BigDecimal.valueOf(expense.getAmount()));

        if (totalWithNewExpense.compareTo(departmentMonthlyBudget) > 0) {
            AlertCreateDto alert = new AlertCreateDto();
            alert.setExpense_id(expense.getExpense_id());
            alert.setMessage("Despesa excedeu o limite mensal do departamento");
            alert.setSeverity("MEDIUM");
            alert.setStatus("NEW");
            alert.setType(2L); // tipo = departamento
            return Optional.of(alert);
        }

        return Optional.empty();
    }
}
