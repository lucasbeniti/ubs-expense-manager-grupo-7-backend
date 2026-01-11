package com.empresa.projeto.gestao_ubs.Dto.Expense;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseCreateDto {
    private Long expense_id;
    private String description;
    private LocalDate date;
    private String receipt_url;
    private String status;
    private BigDecimal amount;
    private Long currency_id;
    private Long employee_id;
    private Long category_id;

}
