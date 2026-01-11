package com.empresa.projeto.gestao_ubs.Dto.Expense;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpensesResponseDto {

    private Long expense_id;
    private String description;
    private LocalDate date;
    private String receipt_url;
    private String status;
    private Double amount;
    private Long currency_id;
    private String currency_code;
    private String currency_name;
    private Double exchange_rate_snapshot;
    private Long employee_id;
    private String employee_name;
    private String employee_role;
    private Long category_id;
    private String category_name;
    private BigDecimal daily_limit;
    private BigDecimal monthly_limit;
    private LocalDateTime created_at;
}
