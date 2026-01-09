package com.empresa.projeto.gestao_ubs.Dto.Report.DepartmentExpense;

import java.math.BigDecimal;

public record DepartmentExpenseDto (
    Long department_id,
    String departmentName,
    BigDecimal monthlyBudget,
    Double maxAmount
){}
