package com.bercomic.youngculture.facade;

import com.bercomic.youngculture.dto.CategoryDTO;

import java.util.List;

public interface CategoryFacade {
    void save(CategoryDTO categoryDTO);
    List<CategoryDTO> findAll();
    CategoryDTO getByName(String name);
}
