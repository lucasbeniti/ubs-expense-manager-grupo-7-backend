package com.empresa.projeto.gestao_ubs.Service;

import com.empresa.projeto.gestao_ubs.Dto.Expense.ExpenseCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Expense.ExpenseResponseDto;
import com.empresa.projeto.gestao_ubs.Dto.Expense.ExpenseUpdateStatusDto;

import java.util.List;

public interface ExpenseService {
    ExpenseResponseDto createExpenses(ExpenseCreateDto dto);

    List<ExpenseResponseDto> getAllExpenses();

    ExpenseResponseDto updateExpenseStatus(Long id, ExpenseUpdateStatusDto dto);
}
