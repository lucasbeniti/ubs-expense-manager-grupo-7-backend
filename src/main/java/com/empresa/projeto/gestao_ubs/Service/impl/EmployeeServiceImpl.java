package com.empresa.projeto.gestao_ubs.Service.impl;

import com.empresa.projeto.gestao_ubs.Dto.Employee.EmployeeCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Employee.EmployeeResponseDto;
import com.empresa.projeto.gestao_ubs.Dto.Employee.EmployeeUpdateDto;
import com.empresa.projeto.gestao_ubs.Entity.Employee;
import com.empresa.projeto.gestao_ubs.Exception.ResourceNotFoundException;
import com.empresa.projeto.gestao_ubs.Mapper.EmployeeMapper;
import com.empresa.projeto.gestao_ubs.Repository.DepartmentRepository;
import com.empresa.projeto.gestao_ubs.Repository.EmployeeRepository;
import com.empresa.projeto.gestao_ubs.Service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public EmployeeResponseDto createEmployee(EmployeeCreateDto dto) {
        Employee employee = EmployeeMapper.toEntity(dto);

        employee.setDepartment(
                departmentRepository.findById(dto.getDepartment_id())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Department not found"))
        );

        if (dto.getManager_id() != null) {
            Employee manager = employeeRepository.findById(dto.getManager_id())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Manager not found"));

            employee.setManager(manager);
        }

        Employee saved = employeeRepository.save(employee);
        return EmployeeMapper.toResponseDto(saved);
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .map(EmployeeMapper::toResponseDto)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));
    }

    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper::toResponseDto)
                .toList();
    }

    @Override
    public EmployeeResponseDto updateEmployee(Long employeeId, EmployeeUpdateDto dto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        EmployeeMapper.updateEntity(employee, dto);

        if (dto.getDepartment_id() != null) {
            employee.setDepartment(
                    departmentRepository.findById(dto.getDepartment_id())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException("Department not found"))
            );
        }

        if (dto.getManager_id() != null) {
            if (dto.getManager_id().equals(employeeId)) {
                throw new IllegalArgumentException("Employee cannot be their own manager");
            }

            Employee manager = employeeRepository.findById(dto.getManager_id())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Manager not found"));

            employee.setManager(manager);
        }

        Employee updated = employeeRepository.save(employee);
        return EmployeeMapper.toResponseDto(updated);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        employeeRepository.delete(employee);
    }
}
