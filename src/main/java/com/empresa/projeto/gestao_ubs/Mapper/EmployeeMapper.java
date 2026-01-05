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
                employee.getDepartment() != null ? employee.getDepartment().getDepartment_id() : null,
                employee.getManager() != null ? employee.getManager().getEmployee_id() : null
        );
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto){
        Employee employee = new Employee();
        employee.setEmployee_id(employeeDto.getEmployee_id());
        employee.setName(employeeDto.getName());
        employee.setCpf(employeeDto.getCpf());
        employee.setEmail(employeeDto.getEmail());
        employee.setRole(employeeDto.getRole());

        return employee;
    }
}
