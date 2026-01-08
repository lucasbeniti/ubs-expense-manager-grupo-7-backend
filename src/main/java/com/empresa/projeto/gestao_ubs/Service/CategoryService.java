package com.empresa.projeto.gestao_ubs.Service;

import com.empresa.projeto.gestao_ubs.Dto.Category.CategoryCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Category.CategoryResponseDto;
import com.empresa.projeto.gestao_ubs.Dto.Category.CategoryUpdateDto;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto createCategory(CategoryCreateDto dto);

    CategoryResponseDto getCategoryById(Long categoryId);

    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto updateCategory(Long categoryId, CategoryUpdateDto dto);

    void deleteCategory(Long categoryId);
}
