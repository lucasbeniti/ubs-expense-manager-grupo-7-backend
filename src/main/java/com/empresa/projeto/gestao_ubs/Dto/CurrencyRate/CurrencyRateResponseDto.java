package com.empresa.projeto.gestao_ubs.Dto.CurrencyRate;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CurrencyRateResponseDto {

    private Long id;
    private Long currencyId;
    private String currencyCode;
    private BigDecimal rate;
    private LocalDate validDate;
}
