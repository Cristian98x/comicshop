package com.bercomic.youngculture.facade;

import com.bercomic.youngculture.dto.ProductDTO;

import java.util.List;

public interface ProductFacade {
    void save(ProductDTO productDto);
    List<ProductDTO> findAll();
    List<ProductDTO> getByCategory(String category);
    ProductDTO getById(String id);
}
