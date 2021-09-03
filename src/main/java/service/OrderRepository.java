package service;

import model.Order;

public interface OrderRepository {
    Order save(Order order);
}
