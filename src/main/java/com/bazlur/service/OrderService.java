package com.bazlur.service;

import com.bazlur.dto.ShippingAddressDTO;
import com.bazlur.eshoppers.domain.ShippingAddress;
import com.bazlur.eshoppers.domain.User;

public interface OrderService {
    void processOrder(ShippingAddressDTO shippingAddress, User currentUser);
}
