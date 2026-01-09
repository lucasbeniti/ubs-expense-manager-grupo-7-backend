package com.empresa.projeto.gestao_ubs.Service.impl;

import com.empresa.projeto.gestao_ubs.Dto.Report.CategoryExpense.CategoryExpenseDto;
import com.empresa.projeto.gestao_ubs.Repository.ExpenseRepository;
import com.empresa.projeto.gestao_ubs.Service.CategoryReportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryReportServiceImpl implements CategoryReportService {
    private final ExpenseRepository expenseRepository;


    @Override
    public List<CategoryExpenseDto> ExpenseByCategory(Long employee_id) {
        return expenseRepository.findExpenseByCategory(employee_id);
    }
}
