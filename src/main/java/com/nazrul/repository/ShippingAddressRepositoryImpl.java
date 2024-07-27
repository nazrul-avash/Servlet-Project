package com.nazrul.repository;

import com.nazrul.eshoppers.domain.ShippingAddress;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class ShippingAddressRepositoryImpl implements ShippingAddressRepository{
    private static final Set<ShippingAddress> SHIPPING_ADDRESSES = new CopyOnWriteArraySet<>();
    @Override
    public ShippingAddress save(ShippingAddress convertTo) {
        SHIPPING_ADDRESSES.add(convertTo);
        return convertTo;
    }
}
