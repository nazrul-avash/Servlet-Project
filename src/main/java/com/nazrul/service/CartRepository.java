package com.nazrul.service;

import com.nazrul.eshoppers.domain.Cart;
import com.nazrul.eshoppers.domain.User;

import java.util.Optional;

public interface CartRepository {
    Optional<Cart> findByUser(User currentUser);
    Cart save(Cart cart);
    Cart update(Cart cart);
}
