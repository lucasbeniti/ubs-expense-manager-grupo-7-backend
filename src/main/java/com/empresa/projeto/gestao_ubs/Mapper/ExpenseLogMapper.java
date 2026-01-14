package com.empresa.projeto.gestao_ubs.Mapper;

import com.empresa.projeto.gestao_ubs.Dto.ExpenseLog.ExpenseLogCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.ExpenseLog.ExpenseLogResponseDto;
import com.empresa.projeto.gestao_ubs.Entity.Employee;
import com.empresa.projeto.gestao_ubs.Entity.Expense;
import com.empresa.projeto.gestao_ubs.Entity.ExpenseLog;
import com.empresa.projeto.gestao_ubs.Enums.ExpenseLogAction;
import com.empresa.projeto.gestao_ubs.Enums.ExpenseStatus;

public class ExpenseLogMapper {

    public static ExpenseLog toEntity(ExpenseLogCreateDto dto) {
        ExpenseLog expenseLog = new ExpenseLog();
        expenseLog.setAction(ExpenseLogAction.valueOf(dto.getAction()));
        expenseLog.setComments(dto.getComments());

        return expenseLog;
    }

    public static ExpenseLogResponseDto toResponseDto(ExpenseLog expenseLog) {
        Employee employee = expenseLog.getEmployee();
        Expense expense = expenseLog.getExpense();

        return new ExpenseLogResponseDto(
                expenseLog.getId(),
                expenseLog.getAction().name(),
                expenseLog.getComments(),

                expense != null ? expense.getId() : null,
                expense != null ? expense.getDescription() : null,

                employee != null ? employee.getId() : null,
                employee != null ? employee.getName() : null,

                expenseLog.getCreatedAt()
        );
    }
}
