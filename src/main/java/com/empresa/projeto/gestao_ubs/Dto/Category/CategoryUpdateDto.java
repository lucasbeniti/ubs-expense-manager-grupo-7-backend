package com.empresa.projeto.gestao_ubs.Dto.Category;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateDto {

    private String name;
    private BigDecimal daily_limit;
    private BigDecimal monthly_limit;
}