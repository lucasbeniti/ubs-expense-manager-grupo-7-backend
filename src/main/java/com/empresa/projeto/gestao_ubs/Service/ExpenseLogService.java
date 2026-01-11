package com.empresa.projeto.gestao_ubs.Service;

import com.empresa.projeto.gestao_ubs.Dto.ExpenseLog.ExpenseLogCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.ExpenseLog.ExpenseLogResponseDto;

public interface ExpenseLogService {
    ExpenseLogResponseDto newExpenseLog(ExpenseLogCreateDto dto);

}
