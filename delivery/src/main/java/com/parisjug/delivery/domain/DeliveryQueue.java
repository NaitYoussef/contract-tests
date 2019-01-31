package com.parisjug.delivery.domain;

import java.util.List;

public interface DeliveryQueue {
    void addInErrorOrder(Order order);
    List<Order> ordersInError();

    void addInProcessOrder(Order order);
    List<Order> ordersInProcess();
    
    void clear();
}
