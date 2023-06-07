package com.gm.pdv.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    @NotBlank(message = "Description field is required.")
    private String description;
    @Column(length = 20, precision = 20, scale = 2, nullable = false)
    @NotNull(message = "Price field is required.")
    private BigDecimal price;
    @Column(nullable = false)
    @NotNull(message = "Quantity field is required.")
    private int quantity;

}
