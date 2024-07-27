package com.nazrul.service;

import com.nazrul.dto.ShippingAddressDTO;
import com.nazrul.eshoppers.domain.User;

public interface OrderService {
    void processOrder(ShippingAddressDTO shippingAddress, User currentUser);
}
