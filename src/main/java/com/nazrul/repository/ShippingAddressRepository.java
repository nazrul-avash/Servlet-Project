package com.nazrul.repository;

import com.nazrul.eshoppers.domain.ShippingAddress;

public interface ShippingAddressRepository {
    ShippingAddress save(ShippingAddress convertTo);
}
