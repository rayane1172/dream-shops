package com.dailycodework.dream_shops.dto;

import com.dailycodework.dream_shops.model.CartItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class CartDto {

    private Long id;
    private BigDecimal totalAmount;
    private Set<CartItemDto> items;

}
