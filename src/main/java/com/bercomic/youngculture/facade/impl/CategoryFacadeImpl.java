package com.bercomic.youngculture.facade.impl;

import com.bercomic.youngculture.converter.CategoryConverter;
import com.bercomic.youngculture.dto.CategoryDTO;
import com.bercomic.youngculture.facade.CategoryFacade;
import com.bercomic.youngculture.model.Category;
import com.bercomic.youngculture.service.CategoryService;
import com.bercomic.youngculture.service.impl.CategoryServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class CategoryFacadeImpl implements CategoryFacade {

    private CategoryService categoryService = new CategoryServiceImpl();
    private CategoryConverter categoryConverter = new CategoryConverter();

    @Override
    public void save(CategoryDTO categoryDTO) {
        categoryService.save(categoryConverter.dtoToEntity(categoryDTO));
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryService.findAll();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for(Category category:categories){
            categoryDTOS.add(categoryConverter.entityToDto(category));
        }
        return categoryDTOS;
    }

    @Override
    public CategoryDTO getByName(String name) {
        if(categoryService.getByName(name)==null){
            return null;
        }
        return categoryConverter.entityToDto(categoryService.getByName(name));
    }
}
