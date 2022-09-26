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
public class CartDTO {

    private Long id;
    private Double totalPrice;
    private UserDTO userDTO;
    private List<CartItemDTO> cartItemDtos;

}
