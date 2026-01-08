package com.empresa.projeto.gestao_ubs.Dto;

import com.empresa.projeto.gestao_ubs.Entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpensesDto {
    private Long expense_id;
    private String description;
    private LocalDate date;
    private String receipt_url;
    private String status;
    private Double amount;
    //private Currency fk_currency_id;
    private Double exchange_rate_snapshot;
    private Long fk_employee_id;
    //private Category fk_category_id
    private LocalDateTime created_at;
}
