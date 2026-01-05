package com.empresa.projeto.gestao_ubs.Dto;

import com.empresa.projeto.gestao_ubs.Entity.Department;
import com.empresa.projeto.gestao_ubs.Entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long employee_id;
    private String name;
    private String cpf;
    private String email;
    private String role;
    private Department department;
    private Employee manager;
}
