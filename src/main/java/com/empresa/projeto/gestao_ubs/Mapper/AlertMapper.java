package com.empresa.projeto.gestao_ubs.Mapper;

import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertResponseDto;
import com.empresa.projeto.gestao_ubs.Entity.Alert;
import com.empresa.projeto.gestao_ubs.Entity.Expense;

public class AlertMapper {

    public static Alert toEntity(AlertCreateDto dto) {
        Alert alert = new Alert();
        alert.setMessage(dto.getMessage());
        alert.setSeverity(dto.getSeverity());
        alert.setStatus(dto.getStatus());
        alert.setType(dto.getType());
        return alert;
    }

    public static AlertResponseDto toResponseDto(Alert alert) {
        Expense expense = alert.getExpense();

        return new AlertResponseDto(
                alert.getAlertId(),
                alert.getMessage(),
                alert.getSeverity(),
                alert.getStatus(),
                alert.getType(),

                expense != null ? expense.getExpenseId() : null,
                expense != null ? expense.getDescription() : null,

                alert.getCreatedAt()
        );
    }
}
