package com.empresa.projeto.gestao_ubs.Mapper;

import com.empresa.projeto.gestao_ubs.Dto.DepartmentDto;
import com.empresa.projeto.gestao_ubs.Entity.Department;

public class DepartmentMapper {
    public static DepartmentDto mapToDepartmentDto(Department department){
        return new DepartmentDto(
                department.getDepartment_id(),
                department.getName(),
                department.getMonthly_budget()
        );
    }

    public static Department mapToDepartment(DepartmentDto departmentDto){
        Department department = new Department();
        department.setDepartment_id(departmentDto.getDepartment_id());
        department.setName(departmentDto.getName());
        department.setMonthly_budget(departmentDto.getMonthly_budget());
        return department;
    }
}
