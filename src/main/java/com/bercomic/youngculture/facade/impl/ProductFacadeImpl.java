package com.bercomic.youngculture.facade.impl;

import com.bercomic.youngculture.converter.ProductConverter;
import com.bercomic.youngculture.dto.ProductDTO;
import com.bercomic.youngculture.facade.ProductFacade;
import com.bercomic.youngculture.model.Category;
import com.bercomic.youngculture.model.Product;
import com.bercomic.youngculture.service.CategoryService;
import com.bercomic.youngculture.service.ProductService;
import com.bercomic.youngculture.service.impl.CategoryServiceImpl;
import com.bercomic.youngculture.service.impl.ProductServiceImpl;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class ProductFacadeImpl implements ProductFacade {
    private ProductService productService = new ProductServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();
    private ProductConverter productConverter = new ProductConverter();

    @Override
    public void save(ProductDTO productDto) {
        Product product = productConverter.dtoToEntity(productDto);
        Category category = categoryService.getByName(productDto.getCategory().getName());
        product.setCategory(category);
        productService.save(product);
    }

    @Override
    public List<ProductDTO> findAll() {
        List<Product> products = productService.findAll();
        List<ProductDTO> productDtos = new ArrayList<>();
        for (Product product : products) {
            productDtos.add(productConverter.entityToDto(product));
        }
        return productDtos;
    }

    @Override
    public List<ProductDTO> getByCategory(String category) {
        List<Product> products = productService.getByCategory(category);
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : products) {
            productDTOS.add(productConverter.entityToDto(product));
        }
        return productDTOS;
    }

    @Override
    public ProductDTO getById(String id) {
        Long idOfProduct = Long.parseLong(id);
        ProductDTO productDTO = new ProductDTO();
        try {
            productDTO = productConverter.entityToDto(productService.getById(idOfProduct));

        }catch (NullPointerException nullPointerException ){
            nullPointerException.printStackTrace();
        }
        return productDTO;
    }
}
