package com.empresa.projeto.gestao_ubs.Dto.Category;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateDto {

    private String name;
    private Integer daily_limit;
    private Integer monthly_limit;
}