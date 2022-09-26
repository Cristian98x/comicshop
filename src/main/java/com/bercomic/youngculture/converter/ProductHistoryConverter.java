package com.bercomic.youngculture.converter;

import com.bercomic.youngculture.dto.ProductHistoryDTO;
import com.bercomic.youngculture.model.ProductHistory;

public class ProductHistoryConverter {
    public ProductHistory dtoToEntity(ProductHistoryDTO productHistoryDTO){
        ProductHistory productHistory = new ProductHistory();
        productHistory.setPrice(productHistoryDTO.getPrice());
        productHistory.setName(productHistoryDTO.getName());
        productHistory.setQuantity(productHistoryDTO.getQuantity());
        return productHistory;
    }

    public ProductHistoryDTO entityToDto(ProductHistory productHistory){
        ProductHistoryDTO productHistoryDTO = new ProductHistoryDTO();
        productHistoryDTO.setName(productHistory.getName());
        productHistoryDTO.setPrice(productHistory.getPrice());
        productHistoryDTO.setQuantity(productHistory.getQuantity());
        return productHistoryDTO;
    }
}
