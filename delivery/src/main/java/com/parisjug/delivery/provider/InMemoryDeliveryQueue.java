package com.parisjug.delivery.provider;

import com.parisjug.delivery.domain.DeliveryQueue;
import com.parisjug.delivery.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class InMemoryDeliveryQueue implements DeliveryQueue {
    private List<Order> ordersInProcess = new ArrayList<>();
    private List<Order> errors = new ArrayList<>();

    @Override
    public void addInErrorOrder(Order order) {
        errors.add(order);
    }

    @Override
    public List<Order> ordersInError() {
        return Collections.unmodifiableList(ordersInError());
    }

    @Override
    public void addInProcessOrder(Order order) {
        ordersInProcess.add(order);
    }

    @Override
    public List<Order> ordersInProcess() {
        return Collections.unmodifiableList(ordersInProcess);
    }

    @Override
    public void clear() {
        ordersInProcess.clear();
    }


}
