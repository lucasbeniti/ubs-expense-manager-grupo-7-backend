package com.empresa.projeto.gestao_ubs.Dto.Department;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentCreateDto {

    private String name;
    private BigDecimal monthlyBudget;
}
