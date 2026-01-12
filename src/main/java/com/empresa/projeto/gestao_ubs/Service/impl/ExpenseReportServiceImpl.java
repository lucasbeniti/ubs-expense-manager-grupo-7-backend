package com.empresa.projeto.gestao_ubs.Service.impl;

import com.empresa.projeto.gestao_ubs.Dto.Report.EmployeeExpense.EmployeeExpenseDto;
import com.empresa.projeto.gestao_ubs.Dto.Report.EmployeeExpense.EmployeeExpenseReportDto;
import com.empresa.projeto.gestao_ubs.Entity.Expense;
import com.empresa.projeto.gestao_ubs.Repository.ExpenseRepository;
import com.empresa.projeto.gestao_ubs.Service.ExpenseReportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExpenseReportServiceImpl implements ExpenseReportService {

    private final ExpenseRepository expenseRepository;

    @Override
    public List<EmployeeExpenseReportDto> getEmployeeExpenseReport(
            LocalDate start,
            LocalDate end,
            Long employee_id,
            Long category_id
    ) {
        List<Expense> expenses =
                expenseRepository.findExpensesForEmployeeReport(start, end, employee_id, category_id);

        Map<Long, List<Expense>> expensesByEmployee =
                expenses.stream().collect(Collectors.groupingBy(
                        e -> e.getEmployee().getId()
                ));

        List<EmployeeExpenseReportDto> report = new ArrayList<>();

        for (List<Expense> employeeExpenses : expensesByEmployee.values()) {
            Expense first = employeeExpenses.getFirst();

            BigDecimal totalAmount = employeeExpenses.stream()
                    .map(Expense::getAmount)
                    .filter(Objects::nonNull)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            List<EmployeeExpenseDto> items =
                    employeeExpenses.stream()
                            .map(e -> new EmployeeExpenseDto(
                                    e.getId(),
                                    e.getDate(),
                                    e.getDescription(),
                                    e.getCategory() != null ? e.getCategory().getName() : null,
                                    e.getAmount(),
                                    e.getCurrency().getCode(),
                                    e.getStatus().name()
                            ))
                            .toList();

            report.add(new EmployeeExpenseReportDto(
                    first.getEmployee().getId(),
                    first.getEmployee().getName(),
                    totalAmount,
                    items
            ));
        }

        return report;
    }

}
