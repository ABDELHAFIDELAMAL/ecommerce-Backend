package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(exclude = {"category", "images", "orderItems", "reviews"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    @Builder.Default
    private Integer quantity = 0;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    @Builder.Default
    private Integer reviewsCount = 0;

    @Column(precision = 3, scale = 2)
    private BigDecimal averageRating;

    @Column(nullable = false)
    @Builder.Default
    private Boolean active = true;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference
    private Category category;


    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<Image> images = new ArrayList<>();


    @OneToMany(mappedBy = "product")
    @Builder.Default
    private List<OrderItem> orderItems = new ArrayList<>();


    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();
}