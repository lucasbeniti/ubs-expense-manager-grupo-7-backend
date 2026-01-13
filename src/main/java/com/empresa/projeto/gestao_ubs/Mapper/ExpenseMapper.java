package com.empresa.projeto.gestao_ubs.Mapper;

import com.empresa.projeto.gestao_ubs.Dto.Expense.ExpenseCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Expense.ExpenseResponseDto;
import com.empresa.projeto.gestao_ubs.Entity.Category;
import com.empresa.projeto.gestao_ubs.Entity.Currency;
import com.empresa.projeto.gestao_ubs.Entity.Employee;
import com.empresa.projeto.gestao_ubs.Entity.Expense;
import com.empresa.projeto.gestao_ubs.Enums.ExpenseStatus;

public class ExpenseMapper {

    public static Expense toEntity(ExpenseCreateDto dto) {
        Expense expense = new Expense();
        expense.setDescription(dto.getDescription());
        expense.setDate(dto.getDate());
        expense.setReceiptUrl(dto.getReceiptUrl());
        expense.setStatus(ExpenseStatus.valueOf(dto.getStatus()));
        expense.setAmount(dto.getAmount());

        return expense;
    }

    public static ExpenseResponseDto toResponseDto(Expense expense) {
        Currency currency = expense.getCurrency();
        Employee employee = expense.getEmployee();
        Category category = expense.getCategory();

        return new ExpenseResponseDto(
                expense.getId(),
                expense.getDescription(),
                expense.getDate(),
                expense.getReceiptUrl(),
                expense.getStatus().name(),
                expense.getNeedReview(),
                expense.getAmount(),

                currency != null ? currency.getId() : null,
                currency != null ? currency.getCode() : null,
                currency != null ? currency.getName() : null,

                expense.getExchangeRateSnapshot(),

                employee != null ? employee.getId() : null,
                employee != null ? employee.getName() : null,
                employee != null ? employee.getRole().name() : null,

                category != null ? category.getId() : null,
                category != null ? category.getName() : null,
                category != null ? category.getDailyLimit() : null,
                category != null ? category.getMonthlyLimit() : null,

                expense.getCreatedAt()
        );
    }
}
