package com.bercomic.youngculture.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private Double price;

    @Column
    @NotNull
    private String type;

    @Column
    private String description;

    @Column(length = 500)
    @NotNull
    private String image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "code")
    private Category category;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "product")
    private List<CartItem> cartItems = new ArrayList<>();


    public Product(String name, Double price, String type, String description, String image, Category category) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;
        this.image = image;
        this.category = category;
    }

}
