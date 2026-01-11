package com.empresa.projeto.gestao_ubs.Service;

import com.empresa.projeto.gestao_ubs.Dto.Report.CategoryExpense.CategoryExpenseDto;

import java.util.List;

public interface CategoryReportService {

    List<CategoryExpenseDto>ExpenseByCategory(
            Long category_id
    ) ;

}


