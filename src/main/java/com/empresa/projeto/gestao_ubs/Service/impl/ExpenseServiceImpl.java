package com.empresa.projeto.gestao_ubs.Service.impl;

import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Expense.ExpenseCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Expense.ExpenseResponseDto;
import com.empresa.projeto.gestao_ubs.Dto.Expense.ExpenseUpdateStatusDto;
import com.empresa.projeto.gestao_ubs.Dto.ExpenseLog.ExpenseLogCreateDto;
import com.empresa.projeto.gestao_ubs.Entity.Expense;
import com.empresa.projeto.gestao_ubs.Enums.ExpenseLogAction;
import com.empresa.projeto.gestao_ubs.Enums.ExpenseStatus;
import com.empresa.projeto.gestao_ubs.Exception.ResourceNotFoundException;
import com.empresa.projeto.gestao_ubs.Infra.Security.AuthenticationFacade;
import com.empresa.projeto.gestao_ubs.Mapper.ExpenseMapper;
import com.empresa.projeto.gestao_ubs.Repository.*;
import com.empresa.projeto.gestao_ubs.Service.AlertService;
import com.empresa.projeto.gestao_ubs.Service.ExpenseLogService;
import com.empresa.projeto.gestao_ubs.Service.ExpenseRulesService;
import com.empresa.projeto.gestao_ubs.Service.ExpenseService;
import jakarta.transaction.Transactional;
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
    private final ExpenseLogService expenseLogService;
    private final AuthenticationFacade authenticationFacade;

    @Transactional
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

        Long authenticatedEmployeeId = authenticationFacade.getAuthenticatedEmployeeId();

        ExpenseLogCreateDto newLog = new ExpenseLogCreateDto();
        newLog.setAction(ExpenseLogAction.CREATED.toString());
        newLog.setExpenseId(expense.getId());
        newLog.setEmployeeId(authenticatedEmployeeId);
        newLog.setComments("Despesa registrada pelo funcionario");
        expenseLogService.newExpenseLog(newLog);

        return ExpenseMapper.toResponseDto(saved);
    }

    @Override
    public List<ExpenseResponseDto> getAllExpenses() {
        List<Expense> expenses = expensesRepository.findAll();
        return expenses.stream()
                .map(ExpenseMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ExpenseResponseDto updateExpenseStatus(Long id, ExpenseUpdateStatusDto dto) {
        Expense expense = expensesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        expense.setStatus(dto.getStatus());

        Long authenticatedEmployeeId = authenticationFacade.getAuthenticatedEmployeeId();

        ExpenseLogCreateDto newLog = new ExpenseLogCreateDto();
        newLog.setExpenseId(expense.getId());
        newLog.setEmployeeId(authenticatedEmployeeId);
        if (expense.getStatus() == ExpenseStatus.MANAGER_APPROVED)
        {
            newLog.setAction(ExpenseLogAction.APPROVED_MANAGER.toString());
            newLog.setComments("Aprovado pelo gestor");
        } else if (expense.getStatus() == ExpenseStatus.FINANCE_APPROVED)
        {
            newLog.setAction(ExpenseLogAction.APPROVED_FINANCE.toString());
            newLog.setComments("Aprovado pelo financeiro");
        } else {
            newLog.setAction(ExpenseLogAction.IGNORED.toString());
            newLog.setComments("Despesa rejeitada");
        }
        expenseLogService.newExpenseLog(newLog);

        expensesRepository.save(expense);

        return ExpenseMapper.toResponseDto(expense);
    }
}
