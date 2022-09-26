package com.bercomic.youngculture.converter;

import com.bercomic.youngculture.dto.ProductDTO;
import com.bercomic.youngculture.model.Product;

public class ProductConverter {

    private CategoryConverter categoryConverter = new CategoryConverter();


    public ProductDTO entityToDto(Product product) {
        ProductDTO productDto = new ProductDTO();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setType(product.getType());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImage());
        productDto.setCategory(categoryConverter.entityToDto(product.getCategory()));
        return productDto;
    }

    public Product dtoToEntity(ProductDTO productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setType(productDto.getType());
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        product.setCategory(categoryConverter.dtoToEntity(productDto.getCategory()));
        return product;
    }
}
