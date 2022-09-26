package com.bercomic.youngculture.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true,name = "number")
    private UUID number;

    @Column(name = "status")
    private String status;

    @Column(name="price")
    private Double price;

    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="userId")
    private User user;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "orderId",nullable = false)
    private List<ProductHistory> productHistories;
}