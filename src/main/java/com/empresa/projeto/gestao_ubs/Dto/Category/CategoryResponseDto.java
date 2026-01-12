package com.empresa.projeto.gestao_ubs.Dto.Category;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto {

    private Long id;
    private String name;
    private BigDecimal dailyLimit;
    private BigDecimal monthlyLimit;
    private LocalDateTime createdAt;
}