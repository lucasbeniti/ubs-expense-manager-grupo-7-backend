package com.empresa.projeto.gestao_ubs.Mapper;

import com.empresa.projeto.gestao_ubs.Dto.ExpensesDto;
import com.empresa.projeto.gestao_ubs.Entity.Expenses;

// TODO remove currency and category comments

public class ExpensesMapper {
    public static ExpensesDto mapToExpensesDto(Expenses expenses){
        return new ExpensesDto(
                expenses.getExpense_id(),
                expenses.getDescription(),
                expenses.getDate(),
                expenses.getReceipt_url(),
                expenses.getStatus(),
                expenses.getAmount(),
                //expenses.getFk_currency_id(),
                expenses.getExchange_rate_snapshot(),
                expenses.getFk_employee_id().getEmployee_id(),
                // expenses.getFk_category_id(),
                expenses.getCreated_at()
        );
    }

    public static Expenses mapToExpenses(ExpensesDto expensesDto){
        Expenses expenses = new Expenses();
        expenses.setExpense_id(expensesDto.getExpense_id());
        expenses.setDescription(expensesDto.getDescription());
        expenses.setDate(expensesDto.getDate());
        expenses.setReceipt_url(expensesDto.getReceipt_url());
        expenses.setStatus(expenses.getStatus());
        expenses.setAmount(expenses.getAmount());
        //expenses.setExchange_rate_snapshot(expenses.getExchange_rate_snapshot());
        //expenses.setCreated_at(expensesDto.getCreated_at());
        return expenses;
    }
}
