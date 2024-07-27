package com.nazrul.service;

import com.nazrul.eshoppers.CartItemRepository;
import com.nazrul.eshoppers.domain.Cart;
import com.nazrul.eshoppers.domain.CartItem;
import com.nazrul.eshoppers.domain.Product;
import com.nazrul.eshoppers.domain.User;
import com.nazrul.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class CartServiceImpl implements CartService{
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    public CartServiceImpl(CartRepository cartRepository,ProductRepository productRepository, CartItemRepository cartItemRepository){
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Cart getCartByUser(User currentUser) {
        Optional<Cart> optionalCart = cartRepository.findByUser(currentUser);
        return optionalCart.orElseGet(()->createNewCart(currentUser));
    }
    private Cart createNewCart(User currentUser){
        Cart cart = new Cart();
        cart.setUser(currentUser);
        return cartRepository.save(cart);
    }
    public void addProductToCart(String productId, Cart cart){
        Product product = findProduct(productId);
        addProductToCart(product,cart);
        updateCart(cart);
    }
    public void removeProductToCart(String productId, Cart cart){
        Product product = findProduct(productId);
        removeProductToCart(product,cart);
        updateCart(cart);
    }
    private Product findProduct(String productId){
        if (productId == null || productId.length()==0){
            throw new IllegalArgumentException("Product id cannot be null");
        }
        Long id = parseProductId(productId);
        return productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product not found by id: " +id));
    }
    private void updateCart(Cart cart){
        Integer totaltotalItem = getTotalItem(cart);
        BigDecimal totalPrice = calculateTotalPrice(cart);
        cart.setTotalItem(totaltotalItem);
        cart.setTotalPrice(totalPrice);
        cartRepository.update(cart);
    }
    private void addProductToCart(Product product, Cart cart){
        var cartItemOptional = findSimilarProductInCart(cart,product);
        var cartItem = cartItemOptional.map(this::increaseQuantityByOne).orElseGet(()->createNewShoppingCartItem(product));
        cart.getCarItems().add(cartItem);
    }
    public void removeProductToCart(Product productToRemove, Cart cart){
        var itemOptional = cart.getCarItems().stream().filter(cartItem -> cartItem.getProduct().equals(productToRemove)).findAny();
        var cartItem = itemOptional.orElseThrow(()->new CartItemNotFoundException("Cart not found by product: "+productToRemove));
        if(cartItem.getQuantity()>1){
            cartItem.setQuantity(cartItem.getQuantity()-1);
            cartItem.setPrice(cartItem.getPrice().subtract(productToRemove.getPrice()));
            cart.getCarItems().add(cartItem);
            cartItemRepository.update(cartItem);
        }
        else {
            cart.getCarItems().remove(cartItem);
            cartItemRepository.remove(cartItem);
        }
    }
    private CartItem createNewShoppingCartItem(Product product){
        var cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
        cartItem.setPrice(product.getPrice());
        return cartItemRepository.save(cartItem);

    }
    private CartItem increaseQuantityByOne(CartItem cartItem){
        cartItem.setQuantity(cartItem.getQuantity()+1);
        BigDecimal totalPrice = cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
        cartItem.setPrice(totalPrice);
        return cartItemRepository.update(cartItem);
    }
    private Optional<CartItem> findSimilarProductInCart(Cart cart, Product product){
        return cart.getCarItems().stream().filter(cartItem -> cartItem.getProduct().equals(product)).findFirst();
    }
    private Integer getTotalItem(Cart cart){
        return cart.getCarItems().stream().map(CartItem::getQuantity).reduce(0,Integer::sum);
    }
    private BigDecimal calculateTotalPrice(Cart cart){
        return cart.getCarItems().stream().map(CartItem::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
    }
    private Long parseProductId(String productId){
        try{
            return Long.parseLong(productId);
        } catch (NumberFormatException ex){
            throw new IllegalArgumentException("Product id must be a number", ex);
        }
    }

}
