package com.empresa.projeto.gestao_ubs.Service.impl;

import com.empresa.projeto.gestao_ubs.Dto.Employee.EmployeeCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Employee.EmployeeResponseDto;
import com.empresa.projeto.gestao_ubs.Dto.Employee.EmployeeUpdateDto;
import com.empresa.projeto.gestao_ubs.Entity.Employee;
import com.empresa.projeto.gestao_ubs.Entity.User;
import com.empresa.projeto.gestao_ubs.Enums.EmployeeRole;
import com.empresa.projeto.gestao_ubs.Enums.UserRole;
import com.empresa.projeto.gestao_ubs.Exception.ResourceNotFoundException;
import com.empresa.projeto.gestao_ubs.Mapper.EmployeeMapper;
import com.empresa.projeto.gestao_ubs.Repository.DepartmentRepository;
import com.empresa.projeto.gestao_ubs.Repository.EmployeeRepository;
import com.empresa.projeto.gestao_ubs.Repository.UserRepository;
import com.empresa.projeto.gestao_ubs.Service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public EmployeeResponseDto createEmployee(EmployeeCreateDto dto) {
        Employee employee = EmployeeMapper.toEntity(dto);

        employee.setDepartment(
                departmentRepository.findById(dto.getDepartmentId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Department not found"))
        );

        if (dto.getManagerId() != null) {
            Employee manager = employeeRepository.findById(dto.getManagerId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Manager not found"));

            employee.setManager(manager);
        }

        User user = new User();
        user.setLogin(dto.getEmail());
        user.setPassword(passwordEncoder.encode("123456"));
        user.setRole(mapEmployeeRoleToUserRole(EmployeeRole.valueOf(dto.getRole())));

        User savedUser = userRepository.save(user);

        employee.setUser(savedUser);

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.toResponseDto(savedEmployee);
    }

    private UserRole mapEmployeeRoleToUserRole(EmployeeRole employeeRole) {
        return switch (employeeRole) {
            case ADMIN -> UserRole.ADMIN;
            case MANAGER -> UserRole.ADMIN;
            case FINANCE -> UserRole.FINANCE;
            case EMPLOYEE -> UserRole.EMPLOYEE;
        };
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

        if (dto.getDepartmentId() != null) {
            employee.setDepartment(
                    departmentRepository.findById(dto.getDepartmentId())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException("Department not found"))
            );
        }

        if (dto.getManagerId() != null) {
            if (dto.getManagerId().equals(employeeId)) {
                throw new IllegalArgumentException("Employee cannot be their own manager");
            }

            Employee manager = employeeRepository.findById(dto.getManagerId())
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
