package com.empresa.projeto.gestao_ubs.Service.impl;

import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertResponseDto;
import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertUpdateDto;
import com.empresa.projeto.gestao_ubs.Entity.Alert;
import com.empresa.projeto.gestao_ubs.Exception.ResourceNotFoundException;
import com.empresa.projeto.gestao_ubs.Mapper.AlertMapper;
import com.empresa.projeto.gestao_ubs.Repository.AlertRepository;
import com.empresa.projeto.gestao_ubs.Repository.ExpenseRepository;
import com.empresa.projeto.gestao_ubs.Service.AlertService;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
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
                expenseRepository.findById(dto.getExpense_id())
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
    public AlertResponseDto updateAlert(@NonNull AlertUpdateDto dto) {
        Alert alert = alertRepository.findById(dto.getAlert_id())
                .orElseThrow(() -> new RuntimeException("Alerta não encontrado"));

        alert.setStatus(dto.getStatus());
        Alert updatedAlert = alertRepository.save(alert);
        return AlertMapper.toResponseDto(updatedAlert);
    }

}
