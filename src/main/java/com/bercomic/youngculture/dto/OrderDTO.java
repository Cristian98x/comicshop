package com.bercomic.youngculture.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String number;
    private String status;
    private Double price;
    private UserDTO userDTO;
    private List<ProductHistoryDTO> productHistoryDTO;
}
