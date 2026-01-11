package com.empresa.projeto.gestao_ubs.Service;

import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertCreateDto;
import com.empresa.projeto.gestao_ubs.Entity.Expense;

import java.util.Optional;

public interface ExpenseRule {
    Optional<AlertCreateDto> check(Expense expense);
}
