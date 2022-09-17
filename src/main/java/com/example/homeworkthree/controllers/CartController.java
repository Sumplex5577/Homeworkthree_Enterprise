package com.example.homeworkthree.controllers;

import com.example.homeworkthree.exceptions.NotFoundException;
import com.example.homeworkthree.models.Cart;
import com.example.homeworkthree.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/shop/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Cart createCartByPersonId(@RequestParam Integer id) throws NotFoundException {
        return cartService.createCartByPersonId(id);
    }

    @PutMapping("/{cartId}/add")
    @ResponseStatus(HttpStatus.OK)
    public Cart addProductByProductIdAndCartId(@RequestParam Integer productId, @PathVariable Integer cartId) throws NotFoundException {
        return cartService.addProductByProductIdAndCartId(productId, cartId);
    }

    @DeleteMapping("/{cartId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Cart removeProductByProductIdAndCartId(@RequestParam Integer productId, @PathVariable Integer cartId) throws NotFoundException {
        return cartService.removeProductByProductIdAndCartId(productId, cartId);
    }


    @DeleteMapping("/{cartId}/clean")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllProductsFromCartById(@PathVariable Integer cartId) throws NotFoundException {
        cartService.removeAllProductsFromCartById(cartId);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Cart> getAllByPersonId(@RequestParam Integer id) throws NotFoundException {
        return cartService.getAllByPersonId(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Cart getCartById(@RequestParam Integer cartId) throws NotFoundException {
        return cartService.getCartById(cartId);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCartById(@RequestParam Integer cartId) throws NotFoundException {
        cartService.removeCartById(cartId);
    }
}