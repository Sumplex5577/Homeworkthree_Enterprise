package com.example.homeworkthree.services;

import com.example.homeworkthree.exceptions.NotFoundException;
import com.example.homeworkthree.model.Cart;

public class CartService {
    String createCartByPersonId(Integer id) throws NotFoundException;

    String addProductByProductIdAndCartId(Integer productId, Integer cartId) throws NotFoundException;

    String removeProductByProductIdAndCartId(Integer productId, Integer cartId) throws NotFoundException;

    void removeAllProductsFromCartById(Integer id) throws NotFoundException;

    List<Cart> getAllByPersonId(Integer id) throws NotFoundException;

    Cart getCartById(Integer id) throws NotFoundException;

    String removeCartById(Integer id) throws NotFoundException;
}
}
