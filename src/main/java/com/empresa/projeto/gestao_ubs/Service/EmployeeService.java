package com.empresa.projeto.gestao_ubs.Service;

import com.empresa.projeto.gestao_ubs.Dto.Employee.EmployeeCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Employee.EmployeeUpdateDto;
import com.empresa.projeto.gestao_ubs.Dto.Employee.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDto createEmployee(EmployeeCreateDto dto);

    EmployeeResponseDto getEmployeeById(Long employeeId);

    List<EmployeeResponseDto> getAllEmployees();

    EmployeeResponseDto updateEmployee(Long employeeId, EmployeeUpdateDto dto);

    void deleteEmployee(Long employeeId);
}
