package com.bercomic.youngculture.converter;

import com.bercomic.youngculture.dto.CategoryDTO;
import com.bercomic.youngculture.model.Category;


public class CategoryConverter {

    public CategoryDTO entityToDto(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryCode(category.getCategoryCode());
        categoryDTO.setName(category.getName());
        categoryDTO.setProduct(category.getProduct());
        return categoryDTO;
    }

    public Category dtoToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryCode(categoryDTO.getCategoryCode());
        category.setName(categoryDTO.getName());
        category.setProduct(categoryDTO.getProduct());

        return category;
    }
}
