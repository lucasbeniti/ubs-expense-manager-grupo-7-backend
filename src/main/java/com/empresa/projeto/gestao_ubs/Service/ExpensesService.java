package com.empresa.projeto.gestao_ubs.Service;

import com.empresa.projeto.gestao_ubs.Dto.Expense.ExpensesCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Expense.ExpensesResponseDto;

import java.util.List;

public interface ExpensesService {
    ExpensesResponseDto createExpenses(ExpensesCreateDto dto);

    List<ExpensesResponseDto> getAllExpenses(); // <--- ajustar aqui
}
