package com.empresa.projeto.gestao_ubs.Service;

import com.empresa.projeto.gestao_ubs.Dto.ExpensesDto;

import java.util.List;

public interface ExpensesService {
    ExpensesDto createExpenses(ExpensesDto expensesDto);

    List<ExpensesDto> getAllExpenses();
}
