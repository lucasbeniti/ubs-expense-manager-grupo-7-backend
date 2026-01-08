package com.empresa.projeto.gestao_ubs.Service;

import com.empresa.projeto.gestao_ubs.Dto.CurrencyRate.*;

import java.util.List;

public interface CurrencyRateService {

    CurrencyRateResponseDto createCurrencyRate(CurrencyRateCreateDto dto);

    CurrencyRateResponseDto getCurrencyRateById(Long currencyRateId);

    List<CurrencyRateResponseDto> getAllCurrencyRates();

    CurrencyRateResponseDto updateCurrencyRate(
            Long currencyRateId,
            CurrencyRateUpdateDto dto
    );

    void deleteCurrencyRate(Long currencyRateId);
}
