package com.empresa.projeto.gestao_ubs.Controller;

import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertResponseDto;
import com.empresa.projeto.gestao_ubs.Dto.Alerts.AlertUpdateDto;
import com.empresa.projeto.gestao_ubs.Service.AlertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Alerts", description = "APIs endpoints for managing alerts")
@AllArgsConstructor
@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    private AlertService alertService;

    @PostMapping
    @Operation(summary = "Add an alert", description = "Create and return created alert")
    public ResponseEntity<AlertResponseDto> newAlert(@RequestBody AlertCreateDto dto) {
        return new ResponseEntity<>(alertService.newAlert(dto), HttpStatus.CREATED);
    }

    @GetMapping()
    @Operation(summary = "Get all alerts", description = "List all alerts")
    public ResponseEntity<List<AlertResponseDto>> getAll() {
        return ResponseEntity.ok(alertService.getAllAlerts());
    }

    @PatchMapping
    @Operation(summary = "Update the status of an alert", description = "Returns the updated alert")
    public ResponseEntity<AlertResponseDto> update(@RequestBody AlertUpdateDto dto) {
        return ResponseEntity.ok(alertService.updateAlert(dto));
    }
}
