package com.empresa.projeto.gestao_ubs.Service.impl;

import com.empresa.projeto.gestao_ubs.Dto.EmployeeDto;
import com.empresa.projeto.gestao_ubs.Entity.Department;
import com.empresa.projeto.gestao_ubs.Entity.Employee;
import com.empresa.projeto.gestao_ubs.Exception.ResourceNotFoundException;
import com.empresa.projeto.gestao_ubs.Mapper.EmployeeMapper;
import com.empresa.projeto.gestao_ubs.Repository.DepartmentRepository;
import com.empresa.projeto.gestao_ubs.Repository.EmployeeRepository;
import com.empresa.projeto.gestao_ubs.Service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Long departmentId = employeeDto.getFk_department_id();
        if (departmentId != null) {
            Department department = departmentRepository.findById(departmentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Department with id " + departmentId + " not found"));
            employee.setDepartment(department);
        } else {
            employee.setDepartment(null);
        }

        Long managerId = employeeDto.getFk_manager_id();
        if (managerId != null) {
            Employee manager = employeeRepository.findById(managerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Manager with id " + managerId + " not found"));
            employee.setManager(manager);
        } else {
            employee.setManager(null);
        }

        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()->new ResourceNotFoundException("Employee with given id: "+employeeId+ " does not exist"));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepository
                .findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee with given id: "+employeeId+ " does not exist"));

        employee.setName(updatedEmployee.getName());
        employee.setCpf(updatedEmployee.getCpf());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setRole(updatedEmployee.getRole());

        Long departmentId = updatedEmployee.getFk_department_id();
        if (departmentId != null) {
            Department department = departmentRepository.findById(departmentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Department with given id: " + departmentId + " does not exist"));
            employee.setDepartment(department);
        } else {
            employee.setDepartment(null);
        }

        Long managerId = updatedEmployee.getFk_manager_id();
        if (managerId != null) {
            Employee manager = employeeRepository.findById(managerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Manager with given id: " + managerId + " does not exist"));

            if (manager.getEmployee_id().equals(employeeId)) {
                throw new IllegalArgumentException("Employee cannot be their own manager");
            }

            employee.setManager(manager);
        } else {
            employee.setManager(null);
        }

        Employee updatedEmployeeObj = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()->new ResourceNotFoundException("Employee with given id: "+employeeId+ " does not exist"));

        employeeRepository.deleteById(employeeId);

    }
}
