package com.empresa.projeto.gestao_ubs.Dto.Expense;

import com.empresa.projeto.gestao_ubs.Enums.ExpenseStatus;
import lombok.Data;

@Data
public class ExpenseUpdateStatusDto {
    private ExpenseStatus status;
}
