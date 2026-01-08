package com.empresa.projeto.gestao_ubs.Controller;

import com.empresa.projeto.gestao_ubs.Dto.Department.DepartmentCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Department.DepartmentResponseDto;
import com.empresa.projeto.gestao_ubs.Dto.Department.DepartmentUpdateDto;
import com.empresa.projeto.gestao_ubs.Service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(
        origins = "http://localhost:3000",
        allowCredentials = "true"

)
@Tag(name = "Department Controller", description = "APIs for managing departments")
@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    @Operation(summary = "Create a new department", description = "Create and return the created department")
    public ResponseEntity<DepartmentResponseDto> create(@RequestBody DepartmentCreateDto dto) {
        return new ResponseEntity<>(departmentService.createDepartment(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get department by id", description = "Returns the id specified department")
    public ResponseEntity<DepartmentResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @GetMapping
    @Operation(summary = "Get all departments", description = "List all departments")
    public ResponseEntity<List<DepartmentResponseDto>> getAll() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a department", description = "Returns the updated department")
    public ResponseEntity<DepartmentResponseDto> update(@PathVariable Long id, @RequestBody DepartmentUpdateDto dto) {
        return ResponseEntity.ok(departmentService.updateDepartment(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a department", description = "Deletes the id specified department")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
