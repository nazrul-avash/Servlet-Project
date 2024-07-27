package com.bazlur.service;

import com.bazlur.eshoppers.domain.Cart;
import com.bazlur.eshoppers.domain.Product;
import com.bazlur.eshoppers.domain.User;

public interface CartService {
    Cart getCartByUser(User currentUser);

    void addProductToCart(String productId, Cart cart);
    void removeProductToCart(String productId, Cart cart);
}
