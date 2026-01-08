package com.empresa.projeto.gestao_ubs.Dto.Employee;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCreateDto {

    private String name;
    private String cpf;
    private String email;
    private String role;
    private Long department_id;
    private Long manager_id;
}
