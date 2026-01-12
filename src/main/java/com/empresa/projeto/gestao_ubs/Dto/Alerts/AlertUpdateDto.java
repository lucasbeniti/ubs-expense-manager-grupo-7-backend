package com.empresa.projeto.gestao_ubs.Dto.Alerts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertUpdateDto {
    private Long id;
    private String status;
}
