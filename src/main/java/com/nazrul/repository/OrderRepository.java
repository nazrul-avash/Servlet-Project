package com.nazrul.repository;

import com.nazrul.eshoppers.domain.Order;

public interface OrderRepository {
    Order save(Order order);
}
