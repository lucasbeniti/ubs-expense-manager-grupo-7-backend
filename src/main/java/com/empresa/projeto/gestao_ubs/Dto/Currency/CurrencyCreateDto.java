package com.empresa.projeto.gestao_ubs.Dto.Currency;

import lombok.Data;

@Data
public class CurrencyCreateDto {

    private String code;
    private String name;
    private Boolean active;
}