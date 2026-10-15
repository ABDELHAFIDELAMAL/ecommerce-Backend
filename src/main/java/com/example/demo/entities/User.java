package com.example.demo.entities;

import com.example.demo.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String fullName ;
    private String email ;
    private String password ;
    private Role role ;

    @OneToOne
    private Cart cart ;

    @OneToMany
    private List<Order> orders = new ArrayList<>();

    @OneToMany
    private List<Review> reviews = new ArrayList<>();
}
