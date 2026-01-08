package com.empresa.projeto.gestao_ubs.Service;

import com.empresa.projeto.gestao_ubs.Dto.Currency.*;

import java.util.List;

public interface CurrencyService {

    CurrencyResponseDto createCurrency(CurrencyCreateDto dto);

    CurrencyResponseDto getCurrencyById(Long currencyId);

    List<CurrencyResponseDto> getAllCurrencies();

    CurrencyResponseDto updateCurrency(Long currencyId, CurrencyUpdateDto dto);

    void deleteCurrency(Long currencyId);
}
