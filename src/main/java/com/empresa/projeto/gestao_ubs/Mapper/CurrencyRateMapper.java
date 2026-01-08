package com.empresa.projeto.gestao_ubs.Mapper;

import com.empresa.projeto.gestao_ubs.Dto.CurrencyRate.*;
import com.empresa.projeto.gestao_ubs.Entity.Currency;
import com.empresa.projeto.gestao_ubs.Entity.CurrencyRate;

public class CurrencyRateMapper {

    public static CurrencyRate toEntity(
            CurrencyRateCreateDto dto,
            Currency currency
    ) {
        CurrencyRate rate = new CurrencyRate();
        rate.setCurrency(currency);
        rate.setRate(dto.getRate());
        rate.setValid_date(dto.getValid_date());
        return rate;
    }

    public static void updateEntity(
            CurrencyRate rate,
            CurrencyRateUpdateDto dto,
            Currency currency
    ) {
        rate.setCurrency(currency);
        rate.setRate(dto.getRate());
        rate.setValid_date(dto.getValid_date());
    }

    public static CurrencyRateResponseDto toResponseDto(CurrencyRate rate) {
        CurrencyRateResponseDto dto = new CurrencyRateResponseDto();
        dto.setCurrency_rate_id(rate.getCurrency_rate_id());
        dto.setCurrency_id(rate.getCurrency().getCurrency_id());
        dto.setCurrency_code(rate.getCurrency().getCode());
        dto.setRate(rate.getRate());
        dto.setValid_date(rate.getValid_date());
        return dto;
    }
}
