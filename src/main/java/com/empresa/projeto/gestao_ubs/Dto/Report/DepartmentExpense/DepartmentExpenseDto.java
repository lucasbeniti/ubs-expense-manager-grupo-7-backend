package com.empresa.projeto.gestao_ubs.Dto.Report.DepartmentExpense;

import java.math.BigDecimal;

public record DepartmentExpenseDto (
    Long departmentId,
    String departmentName,
    BigDecimal monthlyBudget,
    BigDecimal total
){}
