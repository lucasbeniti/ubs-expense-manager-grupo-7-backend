package com.empresa.projeto.gestao_ubs.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//TODO fazer duas rotas DTO

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long department_id;
    private String name;
    private Integer monthly_budget;

    public DepartmentDto(Long department_id, String name){
        this.department_id = department_id;
        this.name = name;
    }
}
