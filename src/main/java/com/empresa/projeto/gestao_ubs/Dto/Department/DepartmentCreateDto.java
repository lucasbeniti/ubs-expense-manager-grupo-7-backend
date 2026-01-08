package com.empresa.projeto.gestao_ubs.Dto.Department;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentCreateDto {

    private String name;
    private Integer monthly_budget;
}
