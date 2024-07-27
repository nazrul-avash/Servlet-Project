package com.nazrul.eshoppers;

import com.nazrul.eshoppers.domain.CartItem;

public interface CartItemRepository {
    CartItem save(CartItem cartItem);
    CartItem update(CartItem cartItem);
    void remove(CartItem cartItem);
}
