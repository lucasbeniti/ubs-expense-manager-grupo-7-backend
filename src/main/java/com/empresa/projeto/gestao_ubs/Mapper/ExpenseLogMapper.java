package com.empresa.projeto.gestao_ubs.Mapper;

import com.empresa.projeto.gestao_ubs.Dto.ExpenseLog.ExpenseLogCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.ExpenseLog.ExpenseLogResponseDto;
import com.empresa.projeto.gestao_ubs.Entity.Employee;
import com.empresa.projeto.gestao_ubs.Entity.Expense;
import com.empresa.projeto.gestao_ubs.Entity.ExpenseLog;

public class ExpenseLogMapper {

    public static ExpenseLog toEntity(ExpenseLogCreateDto dto) {
        ExpenseLog expenseLog = new ExpenseLog();
        expenseLog.setAction(dto.getAction());
        expenseLog.setComments(dto.getComments());

        return expenseLog;
    }

    public static ExpenseLogResponseDto toResponseDto(ExpenseLog expenseLog) {
        Employee employee = expenseLog.getEmployee();
        Expense expense = expenseLog.getExpense();

        return new ExpenseLogResponseDto(
                expenseLog.getExpenseLogId(),
                expenseLog.getAction(),
                expenseLog.getComments(),

                expense != null ? expense.getExpenseId() : null,
                employee != null ? employee.getEmployeeId() : null,

                expenseLog.getCreatedAt()
        );
    }
}
