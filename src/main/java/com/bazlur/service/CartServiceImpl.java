package com.bazlur.service;

import com.bazlur.eshoppers.CartItemRepository;
import com.bazlur.eshoppers.domain.Cart;
import com.bazlur.eshoppers.domain.CartItem;
import com.bazlur.eshoppers.domain.Product;
import com.bazlur.eshoppers.domain.User;
import com.bazlur.repository.ProductRepository;

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
        if (productId == null || productId.length()==0){
            throw new IllegalArgumentException("Product Id cannot be null");
        }
        Long id = parseProductId(productId);
        var product = productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product not found by id: "+id));
        addProductToCart(product,cart);
        Integer totalTotalItem = getTotalItem(cart);
        BigDecimal totalPrice = calculateTotalPrice(cart);
        cart.setTotalItem(totalTotalItem);
        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);
    }
    private void addProductToCart(Product product, Cart cart){
        var cartItemOptional = findSimilarProductInCart(cart,product);
        var cartItem = cartItemOptional.map(this::increaseQuantityByOne).orElseGet(()->createNewShoppingCartItem(product));
        cart.getCarItems().add(cartItem);
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
