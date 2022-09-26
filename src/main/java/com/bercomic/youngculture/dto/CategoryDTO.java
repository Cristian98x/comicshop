package com.bercomic.youngculture.dto;

import com.bercomic.youngculture.model.Product;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {


    private String categoryCode;
    private String name;
    private List<Product> product;

}
