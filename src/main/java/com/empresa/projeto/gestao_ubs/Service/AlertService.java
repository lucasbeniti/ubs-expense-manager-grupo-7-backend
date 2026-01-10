package com.empresa.projeto.gestao_ubs.Service;

import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertResponseDto;
import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertUpdateDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AlertService {

    AlertResponseDto newAlert(AlertCreateDto dto);

    List<AlertResponseDto> findWithFilters(AlertResponseDto dto);

    List<AlertResponseDto> getAllAlerts();

    AlertResponseDto updateAlert(AlertUpdateDto dto);

}
