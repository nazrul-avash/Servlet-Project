package com.nazrul.service;

import com.nazrul.eshoppers.domain.Cart;
import com.nazrul.eshoppers.domain.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CartRepositoryImpl implements CartRepository{
    private static final Map<User, Set<Cart>> carts = new ConcurrentHashMap<>();

    @Override
    public Optional<Cart> findByUser(User currentUser) {
        Set<Cart> carts1 = carts.get(currentUser);
        if(carts1!=null && !carts1.isEmpty()){
            Cart cart = (Cart) carts1.toArray()[carts1.size()-1];
            return Optional.of(cart);

        }
        return Optional.empty();
    }

    @Override
    public Cart save(Cart cart) {
        carts.computeIfPresent(cart.getUser(),(user,carts)->{
           carts.add(cart);
           return carts;
        });
        carts.computeIfAbsent(cart.getUser(),user -> {
            var carts = new LinkedHashSet<Cart>();
            carts.add(cart);
            return carts;
        });
        return cart;
    }

    @Override
    public Cart update(Cart cart) {
        carts.computeIfPresent(cart.getUser(),((user, carts) ->{
            Cart[] objects = carts.toArray(Cart[]::new);
            objects[objects.length-1] = cart;
            return new LinkedHashSet<>(Arrays.asList(objects));
        } ));
        return cart;
    }

}
