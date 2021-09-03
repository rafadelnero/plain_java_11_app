package service;

import java.time.LocalDateTime;
import model.Order;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order create(Order order) {
        order.setCreationDate(LocalDateTime.now());
        return orderRepository.save(order);
    }
}
