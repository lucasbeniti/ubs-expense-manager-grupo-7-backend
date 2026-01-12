package com.empresa.projeto.gestao_ubs.Dto.Department;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponseDto {

    private Long id;
    private String name;
    private BigDecimal monthlyBudget;
    private LocalDateTime createdAt;
}
