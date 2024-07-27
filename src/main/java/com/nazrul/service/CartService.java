package com.nazrul.service;

import com.nazrul.eshoppers.domain.Cart;
import com.nazrul.eshoppers.domain.User;

public interface CartService {
    Cart getCartByUser(User currentUser);

    void addProductToCart(String productId, Cart cart);
    void removeProductToCart(String productId, Cart cart);
}
