package com.bazlur.eshoppers.domain;

import com.bazlur.eshoppers.CartItemRepositoryImpl;
import com.bazlur.repository.ProductRepositoryImpl;
import com.bazlur.service.CartRepositoryImpl;
import com.bazlur.service.CartService;
import com.bazlur.service.CartServiceImpl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Cart {
    private Long id;
    private Set<CartItem> carItems = new HashSet<>();



    private BigDecimal totalPrice;
    private User user;
    private Integer totalItem;

    public Integer getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<CartItem> getCarItems() {
        return carItems;
    }

    public void setCarItems(Set<CartItem> carItems) {
        this.carItems = carItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

   public User getUser() {
        return user;
    }

    public void setUser(User user) {
       this.user = user;
    }
    private  CartServiceImpl cartService = new CartServiceImpl(new CartRepositoryImpl(),new ProductRepositoryImpl(),new CartItemRepositoryImpl());



}
