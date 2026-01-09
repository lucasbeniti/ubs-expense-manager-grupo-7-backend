package com.empresa.projeto.gestao_ubs.Controller;

import com.empresa.projeto.gestao_ubs.Dto.CurrencyRate.*;
import com.empresa.projeto.gestao_ubs.Service.CurrencyRateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Currency Rates", description = "APIs endpoints for managing currency rates")
@RestController
@RequestMapping("/api/currency-rates")
@AllArgsConstructor
public class CurrencyRateController {

    private final CurrencyRateService currencyRateService;

    @PostMapping
    @Operation(summary = "Add a currency rate", description = "Create and return the created currency rate")
    public ResponseEntity<CurrencyRateResponseDto> create(@RequestBody CurrencyRateCreateDto dto) {
        return new ResponseEntity<>(currencyRateService.createCurrencyRate(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get currency rate by id", description = "Returns the id specific currency rate")
    public ResponseEntity<CurrencyRateResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(currencyRateService.getCurrencyRateById(id));
    }

    @GetMapping
    @Operation(summary = "Get all currency rates", description = "List all currency rates")
    public ResponseEntity<List<CurrencyRateResponseDto>> getAll() {
        return ResponseEntity.ok(currencyRateService.getAllCurrencyRates());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a currency rate", description = "Returns the updated currency rate")
    public ResponseEntity<CurrencyRateResponseDto> update(@PathVariable Long id, @RequestBody CurrencyRateUpdateDto dto) {
        return ResponseEntity.ok(currencyRateService.updateCurrencyRate(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a currency rate", description = "Deletes the id specified currency rate")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        currencyRateService.deleteCurrencyRate(id);

        return ResponseEntity.noContent().build();
    }
}
