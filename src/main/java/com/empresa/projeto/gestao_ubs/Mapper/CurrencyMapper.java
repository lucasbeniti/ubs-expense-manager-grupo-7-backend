package com.empresa.projeto.gestao_ubs.Mapper;

import com.empresa.projeto.gestao_ubs.Dto.Currency.*;
import com.empresa.projeto.gestao_ubs.Entity.Currency;

public class CurrencyMapper {

    public static Currency toEntity(CurrencyCreateDto dto) {
        Currency currency = new Currency();
        currency.setCode(dto.getCode());
        currency.setName(dto.getName());
        currency.setActive(dto.getActive());
        return currency;
    }

    public static void updateEntity(Currency currency, CurrencyUpdateDto dto) {
        currency.setCode(dto.getCode());
        currency.setName(dto.getName());
        currency.setActive(dto.getActive());
    }

    public static CurrencyResponseDto toResponseDto(Currency currency) {
        CurrencyResponseDto dto = new CurrencyResponseDto();
        dto.setCurrency_id(currency.getCurrencyId());
        dto.setCode(currency.getCode());
        dto.setName(currency.getName());
        dto.setActive(currency.getActive());
        return dto;
    }
}
