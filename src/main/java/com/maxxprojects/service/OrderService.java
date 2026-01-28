package com.maxxprojects.service;

import com.maxxprojects.model.Order;
import com.maxxprojects.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private List<Order> orders = new ArrayList<>();
    private long counter = 1;

    public Order createOrder(Long userId) {
        Order order = new Order();
        order.setId(counter++);
        order.setUserId(userId);
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedTime(LocalDateTime.now());

        orders.add(order);
        return order;
    }
    public void updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = getOrderById(orderId);
        order.setStatus(newStatus);
    }

    public Order getOrderById(Long orderId) {
        return orders.stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    public List<Order> getAllOrders() {
        return orders;
    }
}
