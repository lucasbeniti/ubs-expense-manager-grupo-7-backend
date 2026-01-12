package com.empresa.projeto.gestao_ubs.Service;

import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertCreateDto;
import com.empresa.projeto.gestao_ubs.Entity.Expense;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseRulesService {
    private final List<ExpenseRule> rules;

    public ExpenseRulesService(List<ExpenseRule> rules){
        this.rules = rules;
    }

    public List<AlertCreateDto> checkAll(Expense expense){
        return rules.stream()
                .map(rule -> rule.check(expense))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
}
