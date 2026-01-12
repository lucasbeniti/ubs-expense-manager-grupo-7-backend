package com.empresa.projeto.gestao_ubs.Dto.ExpenseLog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseLogResponseDto {
    private Long expense_log_id;
    private Long action;
    private String comments;
    private Long expense_id;
    private Long employee_id;
    private LocalDateTime created_at;
}
