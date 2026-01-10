package com.empresa.projeto.gestao_ubs.Repository;

import com.empresa.projeto.gestao_ubs.Entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByStatus(String status);

    List<Alert> findBySeverity(String severity);

    List<Alert> findByStatusAndSeverity(String status, String severity);

}
