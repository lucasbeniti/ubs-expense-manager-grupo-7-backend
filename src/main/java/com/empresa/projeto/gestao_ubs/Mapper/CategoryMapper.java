package com.empresa.projeto.gestao_ubs.Mapper;

import com.empresa.projeto.gestao_ubs.Dto.Category.*;
import com.empresa.projeto.gestao_ubs.Entity.Category;

public class CategoryMapper {

    public static Category toEntity(CategoryCreateDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDailyLimit(dto.getDaily_limit());
        category.setMonthlyLimit(dto.getMonthly_limit());
        return category;
    }

    public static void updateEntity(Category category, CategoryUpdateDto dto) {

        if (dto.getName() != null) {
            category.setName(dto.getName());
        }
        if (dto.getDaily_limit() != null) {
            category.setDailyLimit(dto.getDaily_limit());
        }
        if (dto.getMonthly_limit() != null) {
            category.setMonthlyLimit(dto.getMonthly_limit());
        }
    }

    public static CategoryResponseDto toResponseDto(Category category) {
        return new CategoryResponseDto(
                category.getCategoryId(),
                category.getName(),
                category.getDailyLimit(),
                category.getMonthlyLimit(),
                category.getCreatedAt()
        );
    }
}
