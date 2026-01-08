package com.empresa.projeto.gestao_ubs.Service.impl;

import com.empresa.projeto.gestao_ubs.Dto.ExpensesDto;
import com.empresa.projeto.gestao_ubs.Entity.Expenses;
import com.empresa.projeto.gestao_ubs.Mapper.ExpensesMapper;
import com.empresa.projeto.gestao_ubs.Repository.ExpensesRepository;
import com.empresa.projeto.gestao_ubs.Service.ExpensesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExpensesServiceImpl implements ExpensesService {

    private final ExpensesRepository expensesRepository;

    @Override
    public ExpensesDto createExpenses(ExpensesDto expensesDto) {
        Expenses expense = ExpensesMapper.mapToExpenses(expensesDto);
        Expenses savedExpenses = expensesRepository.save(expense);
        return ExpensesMapper.mapToExpensesDto(savedExpenses);
    }

    @Override
    public List<ExpensesDto> getAllExpenses() {
        List<Expenses> expenses = expensesRepository.findAll();
        return expenses.stream().map(ExpensesMapper::mapToExpensesDto)
                .collect(Collectors.toList());
    }


}
