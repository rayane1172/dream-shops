package com.dailycodework.dream_shops.service.order;

import com.dailycodework.dream_shops.model.Order;

import java.util.List;

public interface IOrderService {

    Order placeOrder(Long userId);
    Order getOrder(Long orderId);

    List<Order> getUserOrders(Long userId);
}
