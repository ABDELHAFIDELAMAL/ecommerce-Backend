package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Integer rating ;
    private String comment ;
    private LocalDateTime createdAt ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id" ,
            nullable = false
    )
    private User user ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "product_id" ,
            nullable = false
    )
    private Product product ;
}
