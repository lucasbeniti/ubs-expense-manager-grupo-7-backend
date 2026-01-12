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
    private String description;
    private LocalDate date;
    private String receiptUrl;
    private String status;
    private BigDecimal amount;
    private Long currencyId;
    private Long employeeId;
    private Long categoryId;

}
