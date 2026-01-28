package com.maxxprojects.service;

import com.maxxprojects.model.Delivery;
import com.maxxprojects.model.OrderStatus;
import com.maxxprojects.model.RobotStatus;

import java.time.Duration;
import java.time.LocalDateTime;

public class DeliveryService {

    private final OrderService orderService;
    private final RobotService robotService;

    public DeliveryService(OrderService orderService, RobotService robotService) {
        this.orderService = orderService;
        this.robotService = robotService;
    }

    public Delivery startDelivery(Long orderId, Long robotId) {
        Delivery delivery = new Delivery();
        delivery.setOrderId(orderId);
        delivery.setRobotId(robotId);
        delivery.setStartTime(LocalDateTime.now());

        orderService.updateOrderStatus(orderId, OrderStatus.DELIVERY_IN_PROGRESS);
        robotService.updateRobotStatus(robotId, RobotStatus.IN_TRANSIT);

        return delivery;
    }

    public void completeDelivery(Delivery delivery) {
        delivery.setEndTime(LocalDateTime.now());

        long minutes = Duration.between(
                delivery.getStartTime(),
                delivery.getEndTime()
        ).toMinutes();

        if (minutes <= 10) {
            orderService.updateOrderStatus(delivery.getOrderId(), OrderStatus.DELIVERED);
        } else {
            orderService.updateOrderStatus(delivery.getOrderId(), OrderStatus.FAILED);
        }

        robotService.updateRobotStatus(delivery.getRobotId(), RobotStatus.AVAILABLE);
    }
}
