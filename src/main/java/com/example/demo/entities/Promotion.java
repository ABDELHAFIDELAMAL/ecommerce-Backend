package com.example.demo.entities;

import com.example.demo.enums.DiscountType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "promotions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name ;

    @Column(length = 1000)
    private String description ;

    private Double discountValue ;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType ;
    private LocalDateTime startDate ;
    private LocalDateTime endDate ;
    private Boolean active ;
    @ManyToMany
    @JoinTable(
            name = "promotion_products" ,
            joinColumns = @JoinColumn(name = "promotion_id") ,
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();
}
