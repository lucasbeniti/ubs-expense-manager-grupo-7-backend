package com.empresa.projeto.gestao_ubs.Mapper;

import com.empresa.projeto.gestao_ubs.Dto.Department.DepartmentCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Department.DepartmentUpdateDto;
import com.empresa.projeto.gestao_ubs.Dto.Department.DepartmentResponseDto;
import com.empresa.projeto.gestao_ubs.Entity.Department;

public class DepartmentMapper {

    public static Department toEntity(DepartmentCreateDto dto) {
        Department department = new Department();
        department.setName(dto.getName());
        department.setMonthlyBudget(dto.getMonthlyBudget());
        return department;
    }

    public static void updateEntity(Department department, DepartmentUpdateDto dto) {
        if (dto.getName() != null) {
            department.setName(dto.getName());
        }
        if (dto.getMonthlyBudget() != null) {
            department.setMonthlyBudget(dto.getMonthlyBudget());
        }
    }

    public static DepartmentResponseDto toResponseDto(Department department) {
        return new DepartmentResponseDto(
                department.getId(),
                department.getName(),
                department.getMonthlyBudget(),
                department.getCreatedAt()
        );
    }
}
