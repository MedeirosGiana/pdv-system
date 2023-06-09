package com.gm.pdv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSaleDTO {
    private long productid;
    @NotNull(message = "Quantity field is required.")
    private int quantity;
}
