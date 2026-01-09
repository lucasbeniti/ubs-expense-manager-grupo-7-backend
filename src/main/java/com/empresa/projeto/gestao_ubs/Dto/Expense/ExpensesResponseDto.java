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

    private Long expenseId;
    private String description;
    private LocalDate date;
    private String receiptUrl;
    private String status;
    private Double amount;

    private Long currencyId;
    private String currencyCode;
    private String currencyName;

    private Double exchangeRateSnapshot;

    private Long employeeId;
    private String employeeName;
    private String employeeRole;

    private Long categoryId;
    private String categoryName;
    private BigDecimal dailyLimit;
    private BigDecimal monthlyLimit;

    private LocalDateTime createdAt;
}
