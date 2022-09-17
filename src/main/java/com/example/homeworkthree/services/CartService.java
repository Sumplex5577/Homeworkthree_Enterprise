package com.example.homeworkthree.services;

import com.example.homeworkthree.exceptions.NotFoundException;
import com.example.homeworkthree.models.Cart;

import java.util.List;

public interface CartService {

    Cart createCartByPersonId(Integer id) throws NotFoundException;

    Cart addProductByProductIdAndCartId(Integer productId, Integer cartId) throws NotFoundException;

    Cart removeProductByProductIdAndCartId(Integer productId, Integer cartId) throws NotFoundException;

    void removeAllProductsFromCartById(Integer cartId) throws NotFoundException;

    List<Cart> getAllByPersonId(Integer id) throws NotFoundException;

    Cart getCartById(Integer id) throws NotFoundException;

    void removeCartById(Integer id) throws NotFoundException;
}
