package com.bazlur.repository;

import com.bazlur.eshoppers.domain.Order;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class OrderRepositoryImpl implements OrderRepository{
    private static final Set<Order> ORDERS = new CopyOnWriteArraySet<>();
    @Override
    public Order save(Order order) {
        ORDERS.add(order);
        return order;
    }
}
