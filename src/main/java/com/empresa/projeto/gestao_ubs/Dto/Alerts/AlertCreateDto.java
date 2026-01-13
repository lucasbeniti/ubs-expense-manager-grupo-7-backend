package com.empresa.projeto.gestao_ubs.Dto.Alerts;

import com.empresa.projeto.gestao_ubs.Enums.AlertStatus;
import com.empresa.projeto.gestao_ubs.Enums.AlertType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertCreateDto {
    private String message;
    private String severity;
    private AlertStatus status;
    private AlertType type;
    private Long expenseId;
}
