package com.empresa.projeto.gestao_ubs.Service.impl;

import com.empresa.projeto.gestao_ubs.Dto.ExpenseLog.ExpenseLogCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.ExpenseLog.ExpenseLogResponseDto;
import com.empresa.projeto.gestao_ubs.Entity.ExpenseLog;
import com.empresa.projeto.gestao_ubs.Exception.ResourceNotFoundException;
import com.empresa.projeto.gestao_ubs.Mapper.ExpenseLogMapper;
import com.empresa.projeto.gestao_ubs.Repository.EmployeeRepository;
import com.empresa.projeto.gestao_ubs.Repository.ExpenseLogRepository;
import com.empresa.projeto.gestao_ubs.Repository.ExpenseRepository;
import com.empresa.projeto.gestao_ubs.Service.ExpenseLogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExpenseLogServiceImpl implements ExpenseLogService {

    private final EmployeeRepository employeeRepository;
    private final ExpenseRepository expenseRepository;
    private final ExpenseLogRepository expenseLogRepository;

    @Override
    public ExpenseLogResponseDto newExpenseLog(ExpenseLogCreateDto dto) {

        ExpenseLog expenseLog = ExpenseLogMapper.toEntity(dto);

        expenseLog.setExpense(
                expenseRepository.findById(dto.getExpenseId())
                        .orElseThrow(() -> new ResourceNotFoundException("Expense not found"))
        );
        expenseLog.setEmployee(
                employeeRepository.findById(dto.getEmployeeId())
                        .orElseThrow(() -> new ResourceNotFoundException("Employee not found"))
        );

        ExpenseLog saved = expenseLogRepository.save(expenseLog);
        return ExpenseLogMapper.toResponseDto(saved);
    }

}
