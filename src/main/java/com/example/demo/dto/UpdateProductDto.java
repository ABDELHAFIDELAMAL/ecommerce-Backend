package com.example.demo.dto;

import com.example.demo.entities.Category;
import com.example.demo.entities.Image;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class UpdateProductDto {
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private String description;
    private String brand ;
    private BigDecimal averageRating;
    private String category;
}
