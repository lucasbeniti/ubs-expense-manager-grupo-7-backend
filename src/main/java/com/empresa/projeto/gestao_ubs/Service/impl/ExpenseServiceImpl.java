package com.empresa.projeto.gestao_ubs.Service.impl;

import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Expense.ExpenseCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Expense.ExpenseResponseDto;
import com.empresa.projeto.gestao_ubs.Entity.Expense;
import com.empresa.projeto.gestao_ubs.Exception.ResourceNotFoundException;
import com.empresa.projeto.gestao_ubs.Mapper.ExpenseMapper;
import com.empresa.projeto.gestao_ubs.Repository.CurrencyRepository;
import com.empresa.projeto.gestao_ubs.Repository.EmployeeRepository;
import com.empresa.projeto.gestao_ubs.Repository.ExpenseRepository;
import com.empresa.projeto.gestao_ubs.Repository.CategoryRepository;
import com.empresa.projeto.gestao_ubs.Service.ExpenseRule;
import com.empresa.projeto.gestao_ubs.Service.AlertService;
import com.empresa.projeto.gestao_ubs.Service.ExpenseRulesService;
import com.empresa.projeto.gestao_ubs.Service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expensesRepository;
    private final CurrencyRepository currencyRepository;
    private final EmployeeRepository employeeRepository;
    private final CategoryRepository categoryRepository;
    private final ExpenseRulesService expenseRuleService;

    private final AlertService alertService;

    @Override
    public ExpenseResponseDto createExpenses(ExpenseCreateDto dto) {

        Expense expense = ExpenseMapper.toEntity(dto);
        // Set FK relations
        expense.setCurrency(
                currencyRepository.findById(dto.getCurrency_id())
                        .orElseThrow(() -> new ResourceNotFoundException("Currency not found"))
        );
        expense.setEmployee(
                employeeRepository.findById(dto.getEmployee_id())
                        .orElseThrow(() -> new ResourceNotFoundException("Employee not found"))
        );
        if (dto.getCategory_id() != null) {
            expense.setCategory(
                    categoryRepository.findById(dto.getCategory_id())
                            .orElseThrow(() -> new ResourceNotFoundException("Category not found"))
            );
        }
        expense.setExchange_rate_snapshot(1.0);
        Expense saved = expensesRepository.save(expense);


        List<AlertCreateDto> alerts = expenseRuleService.checkAll(saved);
        alerts.forEach(alertService::newAlert);



        return ExpenseMapper.toResponseDto(saved);
    }

    @Override
    public List<ExpenseResponseDto> getAllExpenses() {
        List<Expense> expenses = expensesRepository.findAll();
        return expenses.stream()
                .map(ExpenseMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
