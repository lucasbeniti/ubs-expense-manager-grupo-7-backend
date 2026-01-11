package com.empresa.projeto.gestao_ubs.Dto.Report.DepartmentExpense;

import java.math.BigDecimal;

public record DepartmentExpenseDto (
    Long department_id,
    String department_name,
    BigDecimal monthly_budget,
    BigDecimal total
){}
