package com.empresa.projeto.gestao_ubs.Dto.Employee;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDto {

    private Long employee_id;
    private String name;
    private String cpf;
    private String email;
    private String role;

    private Long department_id;
    private String department_name;

    private Long manager_id;
    private String manager_name;

    private LocalDateTime created_at;
}
