package com.empresa.projeto.gestao_ubs.Service;

import com.empresa.projeto.gestao_ubs.Dto.Report.DepartmentExpense.DepartmentExpenseDto;

import java.util.List;

public interface DepartmentReportService {

    List<DepartmentExpenseDto> getMaxExpenseByDepartment(
            Long department_id
    );
}