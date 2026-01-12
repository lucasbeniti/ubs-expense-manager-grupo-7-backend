package com.empresa.projeto.gestao_ubs.Service;

import com.empresa.projeto.gestao_ubs.Dto.ExpenseLog.ExpenseLogCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.ExpenseLog.ExpenseLogResponseDto;

import java.util.List;

public interface ExpenseLogService {
    ExpenseLogResponseDto newExpenseLog(ExpenseLogCreateDto dto);

    List<ExpenseLogResponseDto> getAllExpenseLogs();

}
