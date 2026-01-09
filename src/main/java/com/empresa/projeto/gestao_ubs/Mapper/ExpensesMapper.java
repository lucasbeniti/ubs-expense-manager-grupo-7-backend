package com.empresa.projeto.gestao_ubs.Mapper;

import com.empresa.projeto.gestao_ubs.Dto.Expense.ExpensesCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Expense.ExpensesResponseDto;
import com.empresa.projeto.gestao_ubs.Entity.Category;
import com.empresa.projeto.gestao_ubs.Entity.Currency;
import com.empresa.projeto.gestao_ubs.Entity.Employee;
import com.empresa.projeto.gestao_ubs.Entity.Expenses;

// TODO remove currency and category comments

public class ExpensesMapper {

    public static Expenses toEntity(ExpensesCreateDto dto) {
        Expenses expense = new Expenses();
        expense.setDescription(dto.getDescription());
        expense.setDate(dto.getDate());
        expense.setReceipt_url(dto.getReceipt_url());
        expense.setStatus(dto.getStatus());
        expense.setAmount(dto.getAmount());
        // fkEmployee e fkCategory podem ser mapeados aqui
        return expense;
    }

    public static ExpensesResponseDto toResponseDto(Expenses expense) {
        Currency currency = expense.getCurrency();
        Employee employee = expense.getEmployee();
        Category category = expense.getCategory();

        return new ExpensesResponseDto(
                expense.getExpense_id(),
                expense.getDescription(),
                expense.getDate(),
                expense.getReceipt_url(),
                expense.getStatus(),
                expense.getAmount(),

                currency != null ? currency.getCurrency_id() : null,
                currency != null ? currency.getCode() : null,
                currency != null ? currency.getName() : null,

                expense.getExchange_rate_snapshot(),

                employee != null ? employee.getEmployee_id() : null,
                employee != null ? employee.getName() : null,
                employee != null ? employee.getRole() : null,

                category != null ? category.getCategory_id() : null,
                category != null ? category.getName() : null,
                category != null ? category.getDaily_limit() : null,
                category != null ? category.getMonthly_limit() : null,

                expense.getCreated_at()
        );
    }
}
