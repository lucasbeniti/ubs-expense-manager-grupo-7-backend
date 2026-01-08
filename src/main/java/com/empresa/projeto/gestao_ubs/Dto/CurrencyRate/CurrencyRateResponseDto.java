package com.empresa.projeto.gestao_ubs.Dto.CurrencyRate;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CurrencyRateResponseDto {

    private Long currency_rate_id;
    private Long currency_id;
    private String currency_code;
    private BigDecimal rate;
    private LocalDate valid_date;
}
