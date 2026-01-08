package com.empresa.projeto.gestao_ubs.Dto.Department;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponseDto {

    private Long department_id;
    private String name;
    private Integer monthly_budget;
    private LocalDateTime created_at;
}
