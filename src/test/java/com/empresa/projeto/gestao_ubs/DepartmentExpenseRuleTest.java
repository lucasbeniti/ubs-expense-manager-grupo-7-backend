package com.empresa.projeto.gestao_ubs;

import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertCreateDto;
import com.empresa.projeto.gestao_ubs.Entity.Department;
import com.empresa.projeto.gestao_ubs.Entity.Employee;
import com.empresa.projeto.gestao_ubs.Entity.Expense;
import com.empresa.projeto.gestao_ubs.Repository.ExpenseRepository;
import com.empresa.projeto.gestao_ubs.Service.Rules.DepartmentExpenseRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentExpenseRuleTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private DepartmentExpenseRule rule;

    private Expense expense;
    private Department department;

    @BeforeEach
    void setup() {
        department = new Department();
        department.setId(1L);
        department.setMonthlyBudget(new BigDecimal("1000"));

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setDepartment(department);

        expense = new Expense();
        expense.setId(10L);
        expense.setEmployee(employee);
        expense.setAmount(new BigDecimal("300"));
        expense.setCreatedAt(LocalDateTime.now());
    }

    @Test
    void shouldCreateDepartmentAlert() {
        when(expenseRepository.sumExpenseByDepartment(any(), any(), any()))
                .thenReturn(new BigDecimal("800"));

        Optional<AlertCreateDto> alert = rule.check(expense);

        assertThat(alert).isPresent();
        assertThat(alert.get().getMessage())
                .isEqualTo("Despesa excedeu o limite mensal do departamento");
        assertThat(alert.get().getSeverity()).isEqualTo("MEDIUM");
        assertThat(alert.get().getStatus()).isEqualTo("NEW");
    }

    @Test
    void shouldNotCreateDepartmentAlert() {
        when(expenseRepository.sumExpenseByDepartment(any(), any(), any()))
                .thenReturn(new BigDecimal("600"));

        Optional<AlertCreateDto> alert = rule.check(expense);

        assertThat(alert).isEmpty();
    }

    @Test
    void shouldNotCreateDepartmentAlertWhenDepartmentIsNull() {
        expense.getEmployee().setDepartment(null);

        Optional<AlertCreateDto> alert = rule.check(expense);

        assertThat(alert).isEmpty();
    }
}
