package com.empresa.projeto.gestao_ubs.Controller;


import com.empresa.projeto.gestao_ubs.Dto.Expense.ExpenseCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Expense.ExpenseResponseDto;
import com.empresa.projeto.gestao_ubs.Dto.Expense.ExpenseUpdateStatusDto;
import com.empresa.projeto.gestao_ubs.Service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@AllArgsConstructor
@RestController
@Tag(name = "Expenses", description = "APIs endpoints for managing expenses")
@RequestMapping("/api/expenses")
public class ExpenseController {

    private ExpenseService expensesService;

    @PostMapping
    @Operation(summary = "Add a new expense", description = "Create and return the created expense")
    public ResponseEntity<ExpenseResponseDto> createExpenses(@RequestBody ExpenseCreateDto dto){
        return new ResponseEntity<>(expensesService.createExpenses(dto), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all expenses", description = "List all expenses")
    public ResponseEntity<List<ExpenseResponseDto>> getAllExpenses(){
        List<ExpenseResponseDto> expenses = expensesService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update expense status", description = "Update the status of an expense")
    public ResponseEntity<ExpenseResponseDto> updateStatus(@PathVariable Long id, @RequestBody ExpenseUpdateStatusDto dto) {
        ExpenseResponseDto updatedExpense = expensesService.updateExpenseStatus(id, dto);
        return ResponseEntity.ok(updatedExpense);
    }
}
