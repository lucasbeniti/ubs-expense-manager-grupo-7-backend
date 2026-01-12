package com.empresa.projeto.gestao_ubs.Mapper;

import com.empresa.projeto.gestao_ubs.Dto.Employee.EmployeeCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Employee.EmployeeUpdateDto;
import com.empresa.projeto.gestao_ubs.Dto.Employee.EmployeeResponseDto;
import com.empresa.projeto.gestao_ubs.Entity.Employee;
import com.empresa.projeto.gestao_ubs.Enums.EmployeeRole;

public class EmployeeMapper {

    public static Employee toEntity(EmployeeCreateDto dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setCpf(dto.getCpf());
        employee.setEmail(dto.getEmail());
        employee.setRole(EmployeeRole.valueOf(dto.getRole().toUpperCase()));
        return employee;
    }

    public static void updateEntity(Employee employee, EmployeeUpdateDto dto) {
        if (dto.getName() != null) {
            employee.setName(dto.getName());
        }
        if (dto.getEmail() != null) {
            employee.setEmail(dto.getEmail());
        }
        if (dto.getRole() != null) {
            employee.setRole(EmployeeRole.valueOf(dto.getRole().toUpperCase()));
        }
    }

    public static EmployeeResponseDto toResponseDto(Employee employee) {
        return new EmployeeResponseDto(
                employee.getId(),
                employee.getName(),
                employee.getCpf(),
                employee.getEmail(),
                employee.getRole().name(),

                employee.getDepartment() != null
                        ? employee.getDepartment().getId()
                        : null,

                employee.getDepartment() != null
                        ? employee.getDepartment().getName()
                        : null,

                employee.getManager() != null
                        ? employee.getManager().getId()
                        : null,

                employee.getManager() != null
                        ? employee.getManager().getName()
                        : null,

                employee.getCreatedAt()
        );
    }
}
