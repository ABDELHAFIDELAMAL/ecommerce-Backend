package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter @ToString
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name ;
    private BigDecimal price ;
    private Integer quantity = 0 ;
    private String description ;
    private Integer reviewsCount = 0 ;
    private BigDecimal averageRating ;
    private Boolean active ;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category ;

    @OneToMany(
            mappedBy = "product" ,
            cascade = CascadeType.ALL ,
            orphanRemoval = true
    )
    private List<Image> images = new ArrayList<>();

    @OneToMany
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany
    private List<Review> reviews = new ArrayList<>();

}
