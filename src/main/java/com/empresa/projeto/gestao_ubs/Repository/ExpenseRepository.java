package com.empresa.projeto.gestao_ubs.Repository;

import com.empresa.projeto.gestao_ubs.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
