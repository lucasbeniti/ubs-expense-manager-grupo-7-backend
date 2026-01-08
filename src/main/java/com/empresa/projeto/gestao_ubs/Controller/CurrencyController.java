package com.empresa.projeto.gestao_ubs.Controller;

import com.empresa.projeto.gestao_ubs.Dto.Currency.*;
import com.empresa.projeto.gestao_ubs.Service.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Currencies", description = "APIs endpoints for managing currencies")
@RestController
@RequestMapping("/api/currencies")
@AllArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @PostMapping
    @Operation(summary = "Add a currency", description = "Create and return the created currency")
    public ResponseEntity<CurrencyResponseDto> create(@RequestBody CurrencyCreateDto dto) {
        return new ResponseEntity<>(currencyService.createCurrency(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get currency by id", description = "Returns the id specific currency")
    public ResponseEntity<CurrencyResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(currencyService.getCurrencyById(id));
    }

    @GetMapping
    @Operation(summary = "Get all currencies", description = "List all currencies")
    public ResponseEntity<List<CurrencyResponseDto>> getAll() {
        return ResponseEntity.ok(currencyService.getAllCurrencies());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a currency", description = "Returns the updated currency")
    public ResponseEntity<CurrencyResponseDto> update(@PathVariable Long id, @RequestBody CurrencyUpdateDto dto) {
        return ResponseEntity.ok(currencyService.updateCurrency(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a currency", description = "Deletes the id specified currency")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        currencyService.deleteCurrency(id);

        return ResponseEntity.noContent().build();
    }
}
