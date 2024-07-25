package com.bazlur.eshoppers.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Cart {
    private Long id;
    private Set<CartItem> carItems = new HashSet<>();
    private BigDecimal totalPrice;
    private User user;

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

   private User getUser() {
        return user;
    }

    public void setUser(User user) {
       this.user = user;
    }



}