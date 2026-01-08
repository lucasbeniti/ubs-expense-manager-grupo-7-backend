package com.empresa.projeto.gestao_ubs.Mapper;

import com.empresa.projeto.gestao_ubs.Dto.DepartmentDto;
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
                employee.getDepartment() != null ? DepartmentMapper.mapToDepartmentDto(employee.getDepartment()) : null,
                employee.getManager() != null ? simpleManager(employee.getManager()) : null
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
    private static EmployeeDto simpleManager(Employee manager) {
        return new EmployeeDto(
                manager.getEmployee_id(),
                manager.getName(),
                null,
                null,
                manager.getRole(),
                null,
                null
        );
    }
}
