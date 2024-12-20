package com.dailycodework.dream_shops.dto;

import com.dailycodework.dream_shops.model.Order;
import com.dailycodework.dream_shops.model.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {

    private Long productId;
    private String productBrand;
    private String productName;
    private int quantity;
    private BigDecimal price;



}
