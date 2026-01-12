package com.empresa.projeto.gestao_ubs.Service.Rules;

import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertCreateDto;
import com.empresa.projeto.gestao_ubs.Entity.Expense;
import com.empresa.projeto.gestao_ubs.Repository.ExpenseRepository;
import com.empresa.projeto.gestao_ubs.Service.ExpenseRule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@AllArgsConstructor
public class DCategoryExpenseRule implements ExpenseRule {
    private ExpenseRepository expenseRepository;

    @Override
    public Optional<AlertCreateDto> check(Expense expense) {
        if (expense.getCategory() == null) return Optional.empty();
        if (expense.getCategory().getDaily_limit() == null) return Optional.empty();

        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);
        BigDecimal totalToday =
                expenseRepository.sumExpenseByCategory(
                        expense.getCategory().getCategory_id(),
                        startOfDay,
                        endOfDay
                );

        BigDecimal totalWithNewExpense =
                totalToday.add(BigDecimal.valueOf(expense.getAmount()));

        if (totalWithNewExpense.compareTo(expense.getCategory().getDaily_limit()) > 0) {
            AlertCreateDto alert = new AlertCreateDto();
            alert.setExpense_id(expense.getExpense_id());
            alert.setMessage("Despesa excedeu o limite diário da categoria");
            alert.setSeverity("HIGH");
            alert.setStatus("NEW");
            alert.setType(1L); // tipo = categoria
            return Optional.of(alert);
        }
        return Optional.empty();
    }
}
