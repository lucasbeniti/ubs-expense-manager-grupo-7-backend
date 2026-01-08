package com.empresa.projeto.gestao_ubs.Service.impl;

import com.empresa.projeto.gestao_ubs.Dto.Department.DepartmentCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Department.DepartmentUpdateDto;
import com.empresa.projeto.gestao_ubs.Dto.Department.DepartmentResponseDto;
import com.empresa.projeto.gestao_ubs.Entity.Department;
import com.empresa.projeto.gestao_ubs.Exception.ResourceNotFoundException;
import com.empresa.projeto.gestao_ubs.Mapper.DepartmentMapper;
import com.empresa.projeto.gestao_ubs.Repository.DepartmentRepository;
import com.empresa.projeto.gestao_ubs.Service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentResponseDto createDepartment(DepartmentCreateDto dto) {
        Department department = DepartmentMapper.toEntity(dto);
        Department saved = departmentRepository.save(department);

        return DepartmentMapper.toResponseDto(saved);
    }

    @Override
    public DepartmentResponseDto getDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department with given id: " + departmentId + " does not exist"
                        )
                );

        return DepartmentMapper.toResponseDto(department);
    }

    @Override
    public List<DepartmentResponseDto> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(DepartmentMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentResponseDto updateDepartment(Long departmentId, DepartmentUpdateDto dto) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department with given id: " + departmentId + " does not exist"
                        )
                );

        DepartmentMapper.updateEntity(department, dto);
        Department updated = departmentRepository.save(department);

        return DepartmentMapper.toResponseDto(updated);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department with given id: " + departmentId + " does not exist"
                        )
                );

        departmentRepository.delete(department);
    }
}
