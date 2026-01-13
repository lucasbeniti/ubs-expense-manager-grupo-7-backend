package com.empresa.projeto.gestao_ubs.Service.impl;

import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertResponseDto;
import com.empresa.projeto.gestao_ubs.Entity.Alert;
import com.empresa.projeto.gestao_ubs.Entity.Expense;
import com.empresa.projeto.gestao_ubs.Enums.AlertStatus;
import com.empresa.projeto.gestao_ubs.Exception.ResourceNotFoundException;
import com.empresa.projeto.gestao_ubs.Mapper.AlertMapper;
import com.empresa.projeto.gestao_ubs.Repository.AlertRepository;
import com.empresa.projeto.gestao_ubs.Repository.ExpenseRepository;
import com.empresa.projeto.gestao_ubs.Service.AlertService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlertServiceImpl implements AlertService {

    private final AlertRepository alertRepository;
    private final ExpenseRepository expenseRepository;

    @Override
    public AlertResponseDto newAlert(AlertCreateDto dto) {
        Alert alert = AlertMapper.toEntity(dto);

        alert.setExpense(
                expenseRepository.findById(dto.getExpenseId())
                        .orElseThrow(() -> new ResourceNotFoundException("Expense not found"))
        );

        Alert saved = alertRepository.save(alert);

        return AlertMapper.toResponseDto(saved);
    }

    @Override
    public List<AlertResponseDto> getAllAlerts() {
        List<Alert> alerts = alertRepository.findAll();
        return alerts.stream()
                .map(AlertMapper::toResponseDto)
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public AlertResponseDto updateAlert(Long id) {
        Alert alert = alertRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alert not found"));

        alert.setStatus(AlertStatus.valueOf(String.valueOf(AlertStatus.RESOLVED)));

        Alert updated = alertRepository.save(alert);

        Expense expense = alert.getExpense();

        boolean hasOpenAlerts = alertRepository.existsByExpenseIdAndStatus(expense.getId(), AlertStatus.NEW);

        if (!hasOpenAlerts) {
            expense.setNeedReview(false);
        }

        return AlertMapper.toResponseDto(updated);
    }

}
