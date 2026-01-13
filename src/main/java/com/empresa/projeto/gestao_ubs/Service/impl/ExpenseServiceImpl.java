package com.empresa.projeto.gestao_ubs.Service.impl;

import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Expense.ExpenseCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Expense.ExpenseResponseDto;
import com.empresa.projeto.gestao_ubs.Dto.Expense.ExpenseUpdateStatusDto;
import com.empresa.projeto.gestao_ubs.Entity.Expense;
import com.empresa.projeto.gestao_ubs.Exception.ResourceNotFoundException;
import com.empresa.projeto.gestao_ubs.Mapper.ExpenseMapper;
import com.empresa.projeto.gestao_ubs.Repository.CurrencyRepository;
import com.empresa.projeto.gestao_ubs.Repository.EmployeeRepository;
import com.empresa.projeto.gestao_ubs.Repository.ExpenseRepository;
import com.empresa.projeto.gestao_ubs.Repository.CategoryRepository;
import com.empresa.projeto.gestao_ubs.Service.AlertService;
import com.empresa.projeto.gestao_ubs.Service.ExpenseRulesService;
import com.empresa.projeto.gestao_ubs.Service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        expense.setCurrency(
                currencyRepository.findById(dto.getCurrencyId())
                        .orElseThrow(() -> new ResourceNotFoundException("Currency not found"))
        );
        expense.setEmployee(
                employeeRepository.findById(dto.getEmployeeId())
                        .orElseThrow(() -> new ResourceNotFoundException("Employee not found"))
        );
        if (dto.getCategoryId() != null) {
            expense.setCategory(
                    categoryRepository.findById(dto.getCategoryId())
                            .orElseThrow(() -> new ResourceNotFoundException("Category not found"))
            );
        }
        expense.setExchangeRateSnapshot(BigDecimal.ONE);
        Expense saved = expensesRepository.save(expense);

        List<AlertCreateDto> alerts = expenseRuleService.checkAll(saved);

        if (!alerts.isEmpty()) {
            saved.setNeedReview(true);
            saved = expensesRepository.save(saved);
        }

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

    @Override
    public ExpenseResponseDto updateExpenseStatus(Long id, ExpenseUpdateStatusDto dto) {
        Expense expense = expensesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        expense.setStatus(dto.getStatus());

        expensesRepository.save(expense);

        return ExpenseMapper.toResponseDto(expense);
    }
}
