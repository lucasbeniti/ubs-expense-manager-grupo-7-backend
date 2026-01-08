package com.empresa.projeto.gestao_ubs.Controller;

import com.empresa.projeto.gestao_ubs.Dto.Department.DepartmentCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Department.DepartmentResponseDto;
import com.empresa.projeto.gestao_ubs.Dto.Department.DepartmentUpdateDto;
import com.empresa.projeto.gestao_ubs.Service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(
        origins = "http://localhost:3000",
        allowCredentials = "true"

)
@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    // Create
    @PostMapping
    public ResponseEntity<DepartmentResponseDto> create(@RequestBody DepartmentCreateDto dto) {
        return new ResponseEntity<>(departmentService.createDepartment(dto), HttpStatus.CREATED);
    }

    // Get By Id
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    // Get All
    @GetMapping
    public ResponseEntity<List<DepartmentResponseDto>> getAll() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> update(@PathVariable Long id, @RequestBody DepartmentUpdateDto dto) {
        return ResponseEntity.ok(departmentService.updateDepartment(id, dto));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
