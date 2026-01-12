package com.empresa.projeto.gestao_ubs.Mapper;

import com.empresa.projeto.gestao_ubs.Dto.Category.*;
import com.empresa.projeto.gestao_ubs.Entity.Category;

public class CategoryMapper {

    public static Category toEntity(CategoryCreateDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDailyLimit(dto.getDailyLimit());
        category.setMonthlyLimit(dto.getMonthlyLimit());
        return category;
    }

    public static void updateEntity(Category category, CategoryUpdateDto dto) {

        if (dto.getName() != null) {
            category.setName(dto.getName());
        }
        if (dto.getDailyLimit() != null) {
            category.setDailyLimit(dto.getDailyLimit());
        }
        if (dto.getMonthlyLimit() != null) {
            category.setMonthlyLimit(dto.getMonthlyLimit());
        }
    }

    public static CategoryResponseDto toResponseDto(Category category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getDailyLimit(),
                category.getMonthlyLimit(),
                category.getCreatedAt()
        );
    }
}
