package com.bazlur.repository;

import com.bazlur.eshoppers.domain.ShippingAddress;

public interface ShippingAddressRepository {
    ShippingAddress save(ShippingAddress convertTo);
}
