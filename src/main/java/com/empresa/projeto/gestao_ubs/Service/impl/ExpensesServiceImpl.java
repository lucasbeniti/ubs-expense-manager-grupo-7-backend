package com.empresa.projeto.gestao_ubs.Service.impl;

import com.empresa.projeto.gestao_ubs.Dto.Expense.ExpensesCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Expense.ExpensesResponseDto;
import com.empresa.projeto.gestao_ubs.Entity.Expenses;
import com.empresa.projeto.gestao_ubs.Exception.ResourceNotFoundException;
import com.empresa.projeto.gestao_ubs.Mapper.ExpensesMapper;
import com.empresa.projeto.gestao_ubs.Repository.CurrencyRepository;
import com.empresa.projeto.gestao_ubs.Repository.EmployeeRepository;
import com.empresa.projeto.gestao_ubs.Repository.ExpensesRepository;
import com.empresa.projeto.gestao_ubs.Repository.CategoryRepository;
import com.empresa.projeto.gestao_ubs.Service.ExpensesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExpensesServiceImpl implements ExpensesService {

    private final ExpensesRepository expensesRepository;
    private final CurrencyRepository currencyRepository;
    private final EmployeeRepository employeeRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ExpensesResponseDto createExpenses(ExpensesCreateDto dto) {

        Expenses expense = ExpensesMapper.toEntity(dto);
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
        Expenses saved = expensesRepository.save(expense);

        return ExpensesMapper.toResponseDto(saved);
    }

    @Override
    public List<ExpensesResponseDto> getAllExpenses() {
        List<Expenses> expenses = expensesRepository.findAll();
        return expenses.stream()
                .map(ExpensesMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
