package com.dailycodework.dream_shops.dto;

import com.dailycodework.dream_shops.enums.OrderStatus;
import com.dailycodework.dream_shops.model.OrderItem;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class OrderDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private LocalDate orderDate;
    private BigDecimal totalAmount;
    private String status;
    private List<OrderItemDto> items;

}
