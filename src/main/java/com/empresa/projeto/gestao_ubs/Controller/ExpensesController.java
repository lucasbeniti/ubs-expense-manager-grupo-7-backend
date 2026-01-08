package com.empresa.projeto.gestao_ubs.Controller;


import com.empresa.projeto.gestao_ubs.Dto.ExpensesDto;
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
    public ResponseEntity<ExpensesDto> createExpenses(@RequestBody ExpensesDto expensesDto){
        ExpensesDto savedExpense = expensesService.createExpenses(expensesDto);
        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }

    // Get all Expenses
    @GetMapping
    @Operation(summary = "Get all expenses", description = "List all expenses")
    public ResponseEntity<List<ExpensesDto>> getAllExpenses(){
        List<ExpensesDto> expenses = expensesService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }
}
