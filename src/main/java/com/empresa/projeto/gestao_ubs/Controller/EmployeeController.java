package com.empresa.projeto.gestao_ubs.Controller;

import com.empresa.projeto.gestao_ubs.Dto.Employee.EmployeeCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Employee.EmployeeResponseDto;
import com.empresa.projeto.gestao_ubs.Dto.Employee.EmployeeUpdateDto;
import com.empresa.projeto.gestao_ubs.Service.EmployeeService;
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
@Tag(name = "Employee Controller", description = "APIs for managing employees")
@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // Create
    @PostMapping
    @Operation(summary = "Create a new employee", description = "Create and return the created employee")
    public ResponseEntity<EmployeeResponseDto> create(@RequestBody EmployeeCreateDto dto) {
        return new ResponseEntity<>(employeeService.createEmployee(dto), HttpStatus.CREATED);
    }

    // Get By Id
    @GetMapping("/{id}")
    @Operation(summary = "Get employee by id", description = "Returns the id specified employee")
    public ResponseEntity<EmployeeResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    // Get All
    @GetMapping
    @Operation(summary = "Get all employees", description = "List all employees")
    public ResponseEntity<List<EmployeeResponseDto>> getAll() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // Update
    @PutMapping("/{id}")
    @Operation(summary = "Update a employee", description = "Returns the updated employee")
    public ResponseEntity<EmployeeResponseDto> update(@PathVariable Long id, @RequestBody EmployeeUpdateDto dto) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, dto));
    }

    // Delete
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a employee", description = "Deletes the id specified employee")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }
}
