package com.empresa.projeto.gestao_ubs.Mapper;

import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertResponseDto;
import com.empresa.projeto.gestao_ubs.Entity.Alert;
import com.empresa.projeto.gestao_ubs.Entity.Expense;
import com.empresa.projeto.gestao_ubs.Enums.AlertStatus;
import com.empresa.projeto.gestao_ubs.Enums.AlertType;

public class AlertMapper {

    public static Alert toEntity(AlertCreateDto dto) {
        Alert alert = new Alert();
        alert.setMessage(dto.getMessage());
        alert.setSeverity(dto.getSeverity());
        alert.setStatus(AlertStatus.valueOf(dto.getStatus()));
        alert.setType(AlertType.valueOf(dto.getType()));
        return alert;
    }

    public static AlertResponseDto toResponseDto(Alert alert) {
        Expense expense = alert.getExpense();

        return new AlertResponseDto(
                alert.getAlertId(),
                alert.getMessage(),
                alert.getSeverity(),
                alert.getStatus().name(),
                alert.getType().name(),

                expense != null ? expense.getExpenseId() : null,
                expense != null ? expense.getDescription() : null,

                alert.getCreatedAt()
        );
    }
}
