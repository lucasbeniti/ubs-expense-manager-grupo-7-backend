package com.empresa.projeto.gestao_ubs.Dto.ExpenseLog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseLogResponseDto {
    private Long id;
    private String action;
    private String comments;
    private Long expenseId;
    private Long employeeId;
    private LocalDateTime createdAt;
}
