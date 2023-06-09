package com.gm.pdv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private Long id;
    @NotBlank(message = "Description field is required.")
    private String description;
    @NotNull(message = "Price field is required.")
    private BigDecimal price;
    @NotNull(message = "Quantity field is required.")
    private  int quantity;
}
