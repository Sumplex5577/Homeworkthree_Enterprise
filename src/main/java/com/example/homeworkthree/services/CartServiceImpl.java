package com.example.homeworkthree.services;


import com.example.homeworkthree.exceptions.NotFoundException;
import com.example.homeworkthree.models.Cart;
import com.example.homeworkthree.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService{

    private static Integer cartCount = 0;

    private final CartRepository cartRepository;

    private final ProductService productService;

    private final PersonService personService;

    public CartServiceImpl(CartRepository cartRepository, ProductService productService, PersonService personService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.personService = personService;
    }

    @Override
    public Cart createCartByPersonId(Integer id) throws NotFoundException {
        Cart cart = new Cart();
        cart.setCartId(++cartCount);
        cart.setPerson(personService.getPersonById(id));
        cartRepository.getCarts().put(cart.getCartId(), cart);
        personService.getPersonById(id).getCarts().add(cart);
        return cart;
    }

    @Override
    public Cart addProductByProductIdAndCartId(Integer productId, Integer cartId) throws NotFoundException {
        if (cartRepository.getCarts().containsKey(cartId)) {
            Cart cart = cartRepository.getCarts().get(cartId);
            cart.getProducts().add(productService.getProductById(productId));
            cart.setSum(cart.getSum().add(BigDecimal.valueOf(productService.getProductById(productId).getPrice())));
            return cart;
        } else {
            throw new NotFoundException("Cart with ID #" + cartId + " is not found");
        }
    }

    @Override
    public Cart removeProductByProductIdAndCartId(Integer productId, Integer cartId) throws NotFoundException {
        if (cartRepository.getCarts().containsKey(cartId)) {
            Cart cart = cartRepository.getCarts().get(cartId);
            cart.getProducts().remove(productService.getProductById(productId));
            if (cart.getSum().compareTo(new BigDecimal("0.0")) != 0) {
                cart.setSum(cart.getSum().subtract(BigDecimal.valueOf(productService.getProductById(productId).getPrice())));
            } else {
                cart.setSum(BigDecimal.valueOf(0.0));
            }
            return cart;
        } else {
            throw new NotFoundException("Cart with ID #" + cartId + " is not found");
        }
    }

    @Override
    public void removeAllProductsFromCartById(Integer cartId) throws NotFoundException {
        if (!cartRepository.getCarts().get(cartId).getProducts().isEmpty()) {
            cartRepository.getCarts().get(cartId).getProducts().clear();
            cartRepository.getCarts().get(cartId).setSum(new BigDecimal("0.0"));
        } else {
            throw new NotFoundException("Cart with ID #" + cartId + " is empty");
        }
    }

    @Override
    public List<Cart> getAllByPersonId(Integer id) throws NotFoundException {
        if (personService.getPersonById(id).getId().equals(id)) {
            return cartRepository.getCarts().values().stream()
                    .filter(cart -> cart.getPerson().getId().equals(id)).collect(Collectors.toList());
        } else {
            throw new NotFoundException("Person with ID #" + id + " is not found");
        }
    }

    @Override
    public Cart getCartById(Integer cartId) throws NotFoundException {
        if (cartRepository.getCarts().containsKey(cartId)) {
            return cartRepository.getCarts().get(cartId);
        } else {
            throw new NotFoundException("Cart with ID #" + cartId + " is not found");
        }
    }

    @Override
    public void removeCartById(Integer cartId) throws NotFoundException {
        if (cartRepository.getCarts().containsKey(cartId)) {
            cartRepository.getCarts().remove(cartId);
        } else {
            throw new NotFoundException("Cart with ID #" + cartId + " is not found");
        }
    }
}