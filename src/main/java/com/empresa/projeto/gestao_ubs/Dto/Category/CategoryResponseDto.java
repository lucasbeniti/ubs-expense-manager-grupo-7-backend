package com.empresa.projeto.gestao_ubs.Dto.Category;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto {

    private Long category_id;
    private String name;
    private BigDecimal daily_limit;
    private BigDecimal monthly_limit;
    private LocalDateTime created_at;
}