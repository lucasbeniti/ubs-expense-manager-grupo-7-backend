package com.empresa.projeto.gestao_ubs.Service.impl;

import com.empresa.projeto.gestao_ubs.Dto.Category.CategoryCreateDto;
import com.empresa.projeto.gestao_ubs.Dto.Category.CategoryUpdateDto;
import com.empresa.projeto.gestao_ubs.Dto.Category.CategoryResponseDto;
import com.empresa.projeto.gestao_ubs.Entity.Category;
import com.empresa.projeto.gestao_ubs.Exception.ResourceNotFoundException;
import com.empresa.projeto.gestao_ubs.Mapper.CategoryMapper;
import com.empresa.projeto.gestao_ubs.Repository.CategoryRepository;
import com.empresa.projeto.gestao_ubs.Service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDto createCategory(CategoryCreateDto dto) {
        Category category = CategoryMapper.toEntity(dto);
        Category saved = categoryRepository.save(category);

        return CategoryMapper.toResponseDto(saved);
    }

    @Override
    public CategoryResponseDto getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category with given id: " + categoryId + " does not exist"
                        )
                );

        return CategoryMapper.toResponseDto(category);
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto updateCategory(Long categoryId, CategoryUpdateDto dto) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category with given id: " + categoryId + " does not exist"
                        )
                );

        CategoryMapper.updateEntity(category, dto);
        Category updated = categoryRepository.save(category);

        return CategoryMapper.toResponseDto(updated);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category with given id: " + categoryId + " does not exist"
                        )
                );

        categoryRepository.delete(category);
    }
}
