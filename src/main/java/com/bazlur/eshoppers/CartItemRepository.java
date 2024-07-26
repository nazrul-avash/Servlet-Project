package com.bazlur.eshoppers;

import com.bazlur.eshoppers.domain.CartItem;

public interface CartItemRepository {
    CartItem save(CartItem cartItem);
    CartItem update(CartItem cartItem);
}
