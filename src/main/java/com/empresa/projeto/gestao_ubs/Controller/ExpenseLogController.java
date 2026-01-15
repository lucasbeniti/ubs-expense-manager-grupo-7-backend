package com.empresa.projeto.gestao_ubs.Controller;

import com.empresa.projeto.gestao_ubs.Dto.ExpenseLog.ExpenseLogCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.ExpenseLog.ExpenseLogResponseDto;
import com.empresa.projeto.gestao_ubs.Service.ExpenseLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Expense Logs", description = "APIs endpoints for managing expense logs")
@AllArgsConstructor
@RestController
@RequestMapping("/api/expense-logs")
public class ExpenseLogController {

    private ExpenseLogService expenseLogService;

    @PostMapping
    @Operation(summary = "Add new expense log", description = "Add log")
    public ResponseEntity<ExpenseLogResponseDto> newExpenseLog(@RequestBody ExpenseLogCreateDto dto) {
        return new ResponseEntity<>(expenseLogService.newExpenseLog(dto), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "List all expenses log", description = "List all log")
    public ResponseEntity<List<ExpenseLogResponseDto>> getAll() {
        return ResponseEntity.ok(expenseLogService.getAllExpenseLogs());
    }
}
