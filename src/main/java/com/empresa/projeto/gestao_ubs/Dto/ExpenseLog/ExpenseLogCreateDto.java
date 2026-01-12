package com.empresa.projeto.gestao_ubs.Dto.ExpenseLog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseLogCreateDto {
    private String action;
    private String comments;
    private Long expenseId;
    private Long employeeId;
}
