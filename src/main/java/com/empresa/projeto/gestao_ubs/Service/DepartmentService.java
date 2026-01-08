package com.empresa.projeto.gestao_ubs.Service;

import com.empresa.projeto.gestao_ubs.Dto.Department.DepartmentCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Department.DepartmentUpdateDto;
import com.empresa.projeto.gestao_ubs.Dto.Department.DepartmentResponseDto;

import java.util.List;

public interface DepartmentService {

    DepartmentResponseDto createDepartment(DepartmentCreateDto dto);

    DepartmentResponseDto getDepartmentById(Long departmentId);

    List<DepartmentResponseDto> getAllDepartments();

    DepartmentResponseDto updateDepartment(Long departmentId, DepartmentUpdateDto dto);

    void deleteDepartment(Long departmentId);
}