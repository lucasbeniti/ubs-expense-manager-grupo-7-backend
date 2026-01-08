package com.empresa.projeto.gestao_ubs.Mapper;

import com.empresa.projeto.gestao_ubs.Dto.Category.*;
import com.empresa.projeto.gestao_ubs.Entity.Category;

public class CategoryMapper {

    public static Category toEntity(CategoryCreateDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDaily_limit(dto.getDaily_limit());
        category.setMonthly_limit(dto.getMonthly_limit());
        return category;
    }

    public static void updateEntity(Category category, CategoryUpdateDto dto) {

        if (dto.getName() != null) {
            category.setName(dto.getName());
        }
        if (dto.getDaily_limit() != null) {
            category.setDaily_limit(dto.getDaily_limit());
        }
        if (dto.getMonthly_limit() != null) {
            category.setMonthly_limit(dto.getMonthly_limit());
        }
    }

    public static CategoryResponseDto toResponseDto(Category category) {
        return new CategoryResponseDto(
                category.getCategory_id(),
                category.getName(),
                category.getDaily_limit(),
                category.getMonthly_limit(),
                category.getCreated_at()
        );
    }
}
