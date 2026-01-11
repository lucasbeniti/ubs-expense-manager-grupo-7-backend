package com.empresa.projeto.gestao_ubs.Dto.Alerts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertResponseDto {
    private Long alert_id;
    private String message;
    private String severity;
    private String status;
    private Long type;
    private Long expense_id;
    private LocalDateTime created_at;
}
