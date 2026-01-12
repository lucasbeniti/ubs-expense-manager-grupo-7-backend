package com.empresa.projeto.gestao_ubs.Repository;

import com.empresa.projeto.gestao_ubs.Entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {

}
