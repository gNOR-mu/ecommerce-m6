package com.bootcamp.mvp_m6.service;

import com.bootcamp.mvp_m6.model.Cart;
import com.bootcamp.mvp_m6.model.CartItem;
import com.bootcamp.mvp_m6.model.Product;
import com.bootcamp.mvp_m6.model.User;
import com.bootcamp.mvp_m6.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para manipular el carrito de compras
 *
 * @author Gabriel Norambuena
 * @version 2.0
 * @apiNote Adaptado del mvp m4
 */
@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    private final ProductService productService;

    /**
     * Obtiene el carro para el usuario que posee la sesión iniciada
     *
     * @param user Usuario loggeado
     * @return Carrito perteneciente al usuario
     * @apiNote Si no se encuentra un carro, se le asigna uno nuevo al usuario autenticado
     */
    @Transactional
    public Cart getCart(User user) {
        return cartRepository.findByUser(user).orElseGet(() -> assignToUser(user));
    }


    @Transactional
    private void addToCart(User user, Long productId, int quantity) {
        //primero obtengo el carro del usuario y el producto
        Cart cart = getCart(user);
        Product product = productService.getProduct(productId);

        // verifico si el producto ya lo tengo en el carro
        // si no lo tengo, lo tengo que crear
        // si lo tengo solo le añado la cantidad

        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getId().equals(productId))
                .findFirst()
                .orElseGet(null);

        if (cartItem != null) {
            Integer newQuantity = cartItem.getQuantity() + quantity;
            cartItem.setQuantity(newQuantity);
        } else {
            //nuevo producto
            CartItem newItem = CartItem.builder()
                    .cart(cart)
                    .quantity(quantity)
                    .product(product)
                    .build();

            cart.getItems().add(newItem);
        }
    }


    /**
     * Asigna un nuevo carro a un usuario
     *
     * @param user Usuario sobre el cual asignar el carro
     * @return Carrito asignado
     */
    private Cart assignToUser(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }
}
