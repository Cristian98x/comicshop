package com.bercomic.youngculture.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductHistoryDTO {

    private String name;
    private Double price;
    private Integer quantity;
}
