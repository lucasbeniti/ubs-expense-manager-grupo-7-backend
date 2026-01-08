package com.empresa.projeto.gestao_ubs.Controller;


import com.empresa.projeto.gestao_ubs.Dto.ExpensesDto;
import com.empresa.projeto.gestao_ubs.Service.ExpensesService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/expenses")
public class ExpensesController {

    private ExpensesService expensesService;

    // Add Expense
    @PostMapping
    public ResponseEntity<ExpensesDto> createExpenses(@RequestBody ExpensesDto expensesDto){
        ExpensesDto savedExpense = expensesService.createExpenses(expensesDto);
        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }

    // Get all Expenses
    @GetMapping
    public ResponseEntity<List<ExpensesDto>> getAllExpenses(){
        List<ExpensesDto> expenses = expensesService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }
}
