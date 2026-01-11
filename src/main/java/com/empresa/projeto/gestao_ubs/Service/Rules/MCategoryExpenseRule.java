package com.empresa.projeto.gestao_ubs.Service.Rules;

import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertCreateDto;
import com.empresa.projeto.gestao_ubs.Entity.Expense;
import com.empresa.projeto.gestao_ubs.Repository.ExpenseRepository;
import com.empresa.projeto.gestao_ubs.Service.ExpenseRule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Optional;

@Component
@AllArgsConstructor
public class MCategoryExpenseRule implements ExpenseRule {

    private final ExpenseRepository expenseRepository;

    @Override
    public Optional<AlertCreateDto> check(Expense expense) {
        if (expense.getCategory() == null) return Optional.empty();
        if (expense.getCategory().getMonthly_limit() == null) return Optional.empty();

        YearMonth currentMonth = YearMonth.now();
        LocalDateTime startOfMonth = currentMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = currentMonth.atEndOfMonth().atTime(23, 59, 59);

        BigDecimal totalThisMonth=
                expenseRepository.sumExpenseByCategory(
                        expense.getCategory().getCategory_id(),
                        startOfMonth,
                        endOfMonth
                );

        BigDecimal totalWithNewExpense = totalThisMonth.add(BigDecimal.valueOf(expense.getAmount()));

        if (totalWithNewExpense.compareTo(expense.getCategory().getMonthly_limit()) > 0) {
            AlertCreateDto alert = new AlertCreateDto();
            alert.setExpense_id(expense.getExpense_id());
            alert.setMessage("Despesa excedeu o limite mensal da categoria");
            alert.setSeverity("HIGH");
            alert.setStatus("NEW");
            alert.setType(1L); // tipo = categoria
            return Optional.of(alert);
        }

        return Optional.empty();
    }
}
