package com.empresa.projeto.gestao_ubs.Dto.Employee;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUpdateDto {

    private String name;
    private String email;
    private String role;
    private Long department_id;
    private Long manager_id;
}
