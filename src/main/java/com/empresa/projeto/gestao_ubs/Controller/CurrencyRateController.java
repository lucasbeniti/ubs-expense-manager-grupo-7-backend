package com.empresa.projeto.gestao_ubs.Controller;

import com.empresa.projeto.gestao_ubs.Dto.CurrencyRate.*;
import com.empresa.projeto.gestao_ubs.Service.CurrencyRateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/currency-rates")
@AllArgsConstructor
public class CurrencyRateController {

    private final CurrencyRateService currencyRateService;

    @PostMapping
    public ResponseEntity<CurrencyRateResponseDto> create(@RequestBody CurrencyRateCreateDto dto) {
        return new ResponseEntity<>(currencyRateService.createCurrencyRate(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyRateResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(currencyRateService.getCurrencyRateById(id));
    }

    @GetMapping
    public ResponseEntity<List<CurrencyRateResponseDto>> getAll() {
        return ResponseEntity.ok(currencyRateService.getAllCurrencyRates());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CurrencyRateResponseDto> update(@PathVariable Long id, @RequestBody CurrencyRateUpdateDto dto) {
        return ResponseEntity.ok(currencyRateService.updateCurrencyRate(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        currencyRateService.deleteCurrencyRate(id);

        return ResponseEntity.noContent().build();
    }
}
