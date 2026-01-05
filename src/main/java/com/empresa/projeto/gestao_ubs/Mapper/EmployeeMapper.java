package com.empresa.projeto.gestao_ubs.Mapper;

import com.empresa.projeto.gestao_ubs.Dto.EmployeeDto;
import com.empresa.projeto.gestao_ubs.Entity.Employee;

public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto(Employee employee){
        return new EmployeeDto(
                employee.getEmployee_id(),
                employee.getName(),
                employee.getCpf(),
                employee.getEmail(),
                employee.getRole(),
                employee.getDepartment(),
                employee.getManager()
        );
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto){
        return new Employee(
                employeeDto.getEmployee_id(),
                employeeDto.getName(),
                employeeDto.getCpf(),
                employeeDto.getEmail(),
                employeeDto.getRole(),
                employeeDto.getDepartment(),
                employeeDto.getManager()
        );
    }
}
