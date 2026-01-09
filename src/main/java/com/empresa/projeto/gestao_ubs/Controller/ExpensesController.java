package com.empresa.projeto.gestao_ubs.Controller;


import com.empresa.projeto.gestao_ubs.Dto.Expense.ExpensesCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Expense.ExpensesResponseDto;
import com.empresa.projeto.gestao_ubs.Entity.Expenses;
import com.empresa.projeto.gestao_ubs.Repository.ExpensesRepository;
import com.empresa.projeto.gestao_ubs.Service.ExpensesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@Tag(name = "Expenses Controller", description = "APIs for managing expenses")
@RequestMapping("/api/expenses")
public class ExpensesController {

    private ExpensesService expensesService;

    // Add Expense
    @PostMapping
    @Operation(summary = "Add a new expense", description = "Create and return the created expense")
    public ResponseEntity<ExpensesResponseDto> createExpenses(@RequestBody ExpensesCreateDto dto){
        return new ResponseEntity<>(expensesService.createExpenses(dto), HttpStatus.CREATED);
    }

    // Get all Expenses
    @GetMapping
    @Operation(summary = "Get all expenses", description = "List all expenses")
    public ResponseEntity<List<ExpensesResponseDto>> getAllExpenses(){
        List<ExpensesResponseDto> expenses = expensesService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }
}
