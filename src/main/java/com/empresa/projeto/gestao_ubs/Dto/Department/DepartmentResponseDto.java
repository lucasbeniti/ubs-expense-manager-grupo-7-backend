package com.empresa.projeto.gestao_ubs.Dto.Department;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponseDto {

    private Long department_id;
    private String name;
    private BigDecimal monthly_budget;
    private LocalDateTime created_at;
}
