package com.empresa.projeto.gestao_ubs.Service.impl;

import com.empresa.projeto.gestao_ubs.Dto.Report.DepartmentExpense.DepartmentExpenseDto;
import com.empresa.projeto.gestao_ubs.Repository.ExpenseRepository;
import com.empresa.projeto.gestao_ubs.Service.DepartmentReportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentReportServiceImpl implements DepartmentReportService {

    private final ExpenseRepository expenseRepository;

    @Override
    public List<DepartmentExpenseDto> getDepartmentBudgetComparative(
            Long department_id
    ) {
        return expenseRepository.findExpenseByDepartment(department_id);
    }
}