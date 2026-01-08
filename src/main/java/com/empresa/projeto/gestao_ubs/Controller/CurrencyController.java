package com.empresa.projeto.gestao_ubs.Controller;

import com.empresa.projeto.gestao_ubs.Dto.Currency.*;
import com.empresa.projeto.gestao_ubs.Service.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/currencies")
@AllArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @PostMapping
    public ResponseEntity<CurrencyResponseDto> create(@RequestBody CurrencyCreateDto dto) {
        return new ResponseEntity<>(currencyService.createCurrency(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(currencyService.getCurrencyById(id));
    }

    @GetMapping
    public ResponseEntity<List<CurrencyResponseDto>> getAll() {
        return ResponseEntity.ok(currencyService.getAllCurrencies());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CurrencyResponseDto> update(@PathVariable Long id, @RequestBody CurrencyUpdateDto dto) {
        return ResponseEntity.ok(currencyService.updateCurrency(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        currencyService.deleteCurrency(id);

        return ResponseEntity.noContent().build();
    }
}
