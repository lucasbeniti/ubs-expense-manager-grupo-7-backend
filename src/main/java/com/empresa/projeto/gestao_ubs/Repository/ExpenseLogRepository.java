package com.empresa.projeto.gestao_ubs.Repository;

import com.empresa.projeto.gestao_ubs.Entity.ExpenseLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseLogRepository extends JpaRepository <ExpenseLog, Long> {

}
