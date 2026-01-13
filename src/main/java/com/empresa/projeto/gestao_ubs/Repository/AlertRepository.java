package com.empresa.projeto.gestao_ubs.Repository;

import com.empresa.projeto.gestao_ubs.Entity.Alert;
import com.empresa.projeto.gestao_ubs.Enums.AlertStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    boolean existsByExpenseIdAndStatus(Long expenseId, AlertStatus status);
}
