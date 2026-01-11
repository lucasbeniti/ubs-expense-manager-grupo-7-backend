package com.empresa.projeto.gestao_ubs.Controller;

import com.empresa.projeto.gestao_ubs.Dto.ExpenseLog.ExpenseLogCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.ExpenseLog.ExpenseLogResponseDto;
import com.empresa.projeto.gestao_ubs.Service.ExpenseLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@Tag(name = "Expense Logs", description = "APIs endpoints for managing expense logs")
@AllArgsConstructor
@RestController
@RequestMapping("/api/expenseLogs")
public class ExpenseLogController {

    private ExpenseLogService expenseLogService;

    public ResponseEntity<ExpenseLogResponseDto> newExpenseLog(@RequestBody ExpenseLogCreateDto dto) {
        return new ResponseEntity<>(expenseLogService.newExpenseLog(dto), HttpStatus.CREATED);
    }
}
