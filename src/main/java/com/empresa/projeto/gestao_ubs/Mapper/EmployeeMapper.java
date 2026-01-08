package com.empresa.projeto.gestao_ubs.Mapper;

import com.empresa.projeto.gestao_ubs.Dto.Employee.EmployeeCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Employee.EmployeeUpdateDto;
import com.empresa.projeto.gestao_ubs.Dto.Employee.EmployeeResponseDto;
import com.empresa.projeto.gestao_ubs.Entity.Employee;

public class EmployeeMapper {

    public static Employee toEntity(EmployeeCreateDto dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setCpf(dto.getCpf());
        employee.setEmail(dto.getEmail());
        employee.setRole(dto.getRole());
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
            employee.setRole(dto.getRole());
        }
    }

    public static EmployeeResponseDto toResponseDto(Employee employee) {
        return new EmployeeResponseDto(
                employee.getEmployee_id(),
                employee.getName(),
                employee.getCpf(),
                employee.getEmail(),
                employee.getRole(),

                employee.getDepartment() != null
                        ? employee.getDepartment().getDepartment_id()
                        : null,

                employee.getDepartment() != null
                        ? employee.getDepartment().getName()
                        : null,

                employee.getManager() != null
                        ? employee.getManager().getEmployee_id()
                        : null,

                employee.getManager() != null
                        ? employee.getManager().getName()
                        : null,

                employee.getCreated_at()
        );
    }
}
