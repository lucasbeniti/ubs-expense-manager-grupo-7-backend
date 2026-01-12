package com.empresa.projeto.gestao_ubs.Dto.Employee;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDto {

    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String role;

    private Long departmentId;
    private String departmentName;

    private Long managerId;
    private String managerName;

    private LocalDateTime createdAt;
}
