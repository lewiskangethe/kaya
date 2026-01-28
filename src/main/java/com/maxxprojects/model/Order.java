package com.maxxprojects.model;

import java.time.LocalDateTime;

public class Order {
    private Long id;
    private Long userId;
    private OrderStatus status;
    private LocalDateTime createdTime;

    public Order(Long id, Long userId, OrderStatus status, LocalDateTime createdTime) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.createdTime = createdTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}
