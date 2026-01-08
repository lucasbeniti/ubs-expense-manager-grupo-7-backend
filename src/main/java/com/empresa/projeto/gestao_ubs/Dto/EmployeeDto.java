package com.empresa.projeto.gestao_ubs.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto {
    private Long employee_id;
    private String name;
    private String cpf;
    private String email;
    private String role;
    private DepartmentDto department;
    private EmployeeDto manager;

    public EmployeeDto(Long employee_id, String name, String cpf, String email,
                       String role, DepartmentDto department, EmployeeDto manager) {
        this.employee_id = employee_id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.role = role;
        this.department = department;
        this.manager = manager;
    }

}
