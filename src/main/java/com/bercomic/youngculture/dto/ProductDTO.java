package com.bercomic.youngculture.dto;
import com.bercomic.youngculture.model.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private Double price;
    private String type;
    private String description;
    private String image;
    private CategoryDTO category;
    private List<Cart> cart;


    public ProductDTO(String name, Double price, String type, String description, String image, CategoryDTO category) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;
        this.image = image;
        this.category = category;
    }

}
