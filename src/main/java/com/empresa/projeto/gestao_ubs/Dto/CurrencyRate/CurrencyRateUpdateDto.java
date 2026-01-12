package com.empresa.projeto.gestao_ubs.Dto.CurrencyRate;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CurrencyRateUpdateDto {

    private Long currencyId;
    private BigDecimal rate;
    private LocalDate valid_date;
}
