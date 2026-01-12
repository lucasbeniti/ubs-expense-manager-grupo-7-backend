package com.empresa.projeto.gestao_ubs.Service;

import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertResponseDto;

import java.util.List;

public interface AlertService {

    AlertResponseDto newAlert(AlertCreateDto dto);

    List<AlertResponseDto> getAllAlerts();

    AlertResponseDto updateAlert(Long id);

}
