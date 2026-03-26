package com.bootcamp.mvp_m6.service;

import com.bootcamp.mvp_m6.dto.cart.CartSummaryDTO;
import com.bootcamp.mvp_m6.mapper.CartMapper;
import com.bootcamp.mvp_m6.model.Cart;
import com.bootcamp.mvp_m6.model.CartItem;
import com.bootcamp.mvp_m6.model.Product;
import com.bootcamp.mvp_m6.model.User;
import com.bootcamp.mvp_m6.repository.CartRepository;
import com.bootcamp.mvp_m6.strategy.discount.DiscountRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    private final CartMapper cartMapper;
    private final List<DiscountRule> discountRules;

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


    /**
     * Añade un producto al carro actual del usuario
     * @param user Usuario sobre el cual añadir el producto
     * @param productId Id del producto a añadir
     * @param quantity Cantidad a añadir
     */
    @Transactional
    public void addProductToCart(User user, Long productId, int quantity) {

        //primero obtengo el carro del usuario y el producto
        Cart cart = getCart(user);
        Product product = productService.getProduct(productId);

        // verifico si el producto ya lo tengo en el carro
        // si no lo tengo, lo tengo que crear
        // si lo tengo solo le añado la cantidad

        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

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

    @Transactional(readOnly = true)
    public CartSummaryDTO getCartSummary(User user) {
        Cart cart = getCart(user);
        List<String> discountConditions = new ArrayList<>();


        BigDecimal subtotal = cart.getItems().stream()
                .map(CartItem::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalDiscount = BigDecimal.ZERO;

        //recorre todas las reglas de descuento, y en caso de ser aplicable, le sumo el descuento
        for (DiscountRule rule : discountRules) {
            if (rule.isApplicable(cart)) {
                BigDecimal ruleDiscount = rule.calculateDiscount(cart);
                totalDiscount = totalDiscount.add(ruleDiscount);

                discountConditions.add(rule.getCondition());
            }
        }

        //si el descuento es negativo significaría que le aumento el precio, no debería pasar, pero por si acaso...
        if(totalDiscount.compareTo(BigDecimal.ZERO) < 0){
            totalDiscount = BigDecimal.ZERO;
        }

        //TODO tal vez sea mejor separar la lógica como el checkout del mvp2, verlo cuando implemente el checkout o lo dejo dentro del cartService
        // como es un porcentaje divide por 100
        BigDecimal discountedPrice = subtotal.multiply(totalDiscount.movePointLeft(2));
        BigDecimal totalFinal = subtotal.subtract(discountedPrice);

        if (totalFinal.compareTo(BigDecimal.ZERO) < 0) {
            totalFinal = BigDecimal.ZERO;
        }

        return cartMapper.toSummaryDTO(cart, subtotal, totalDiscount, totalFinal, discountConditions);
    }

    public int getCarItemCount(User user) {
        Cart cart = getCart(user);

        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            return 0;
        }

        return cart.getItems().stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
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

    @Transactional
    public void removeFromCart(User user, Long productId) {
        Cart cart = getCart(user);

        cart.getItems()
                .removeIf(item -> item.getProduct().getId().equals(productId));
    }
}
