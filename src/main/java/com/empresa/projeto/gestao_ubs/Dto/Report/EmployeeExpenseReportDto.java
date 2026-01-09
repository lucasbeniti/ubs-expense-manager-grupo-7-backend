package com.empresa.projeto.gestao_ubs.Dto.Report;

import java.util.List;

public record EmployeeExpenseReportDto(
        Long employeeId,
        String employeeName,
        Double totalAmount,
        List<EmployeeExpenseDto> expenses
) {}