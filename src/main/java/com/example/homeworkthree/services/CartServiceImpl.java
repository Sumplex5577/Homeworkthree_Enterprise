package com.example.homeworkthree.services;

import com.example.homeworkthree.exceptions.NotFoundException;
import com.example.homeworkthree.model.Cart;
import com.example.homeworkthree.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    private static final String FILE_PATH = "src/main/resources/carts.csv";
    private final CartRepository cartRepository;
    private final ProductService productService;
    private final PersonService personService;
    private final CsvWriter<CartCsvDto> csvWriter;

    public CartServiceImpl(CartRepository cartRepository, ProductService productService,
                           PersonService personService, CsvWriter<CartCsvDto> csvWriter) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.personService = personService;
        this.csvWriter = csvWriter;
    }

    @Override
    public String createCartByPersonId(Integer id) throws NotFoundException {
        Cart cart = new Cart();
        cart.setPerson(personService.getPersonById(id));
        cartRepository.getCarts().put(cart.getId(), cart);
        personService.getPersonById(id).getCarts().add(cart);
        return cart.toString();
    }

    @Override
    public String addProductByProductIdAndCartId(Integer productId, Integer cartId) throws NotFoundException {
        if (cartRepository.getCarts().containsKey(cartId)) {
            Cart cart = cartRepository.getCarts().get(cartId);
            cart.getProducts().add(productService.getProductById(productId));
            cart.setSum(cart.getSum().add(BigDecimal.valueOf(productService.getProductById(productId).getPrice())));
            csvWriter.write(FILE_PATH, buildCartCsvDto(cart, productId, "added"));
            return "Product with ID #" + productId + " is added to cart #" + cartId;
        } else {
            throw new NotFoundException("Cart with ID #" + cartId + " is not found");
        }
    }

    private CartCsvDto buildCartCsvDto(Cart cart, Integer productId, String status) throws NotFoundException {
        return CartCsvDto.builder()
                .id(cart.getId()).ownerId(cart.getPerson().getId())
                .ownerName(cart.getPerson().getFirstName()).ownerLastName(cart.getPerson().getLastName())
                .productName(productService.getProductById(productId).getProductName())
                .productPrice(productService.getProductById(productId).getPrice())
                .sum(cart.getSum().doubleValue())
                .status(status).build();
    }

    @Override
    public String removeProductByProductIdAndCartId(Integer productId, Integer cartId) throws NotFoundException {
        if (cartRepository.getCarts().containsKey(cartId)) {
            Cart cart = cartRepository.getCarts().get(cartId);
            cart.getProducts().remove(productService.getProductById(productId));
            if (cart.getSum().compareTo(new BigDecimal("0.0")) != 0) {
                cart.setSum(cart.getSum().subtract(BigDecimal.valueOf(productService.getProductById(productId).getPrice())));
            } else {
                cart.setSum(BigDecimal.valueOf(0.0));
            }
            csvWriter.write(FILE_PATH, buildCartCsvDto(cart, productId, "deleted"));
            return "Product with ID #" + productId + " is deleted from cart #" + cartId;
        } else {
            throw new NotFoundException("Cart with ID #" + cartId + " is not found");
        }
    }

    @Override
    public void removeAllProductsFromCartById(Integer id) throws NotFoundException {
        if (!cartRepository.getCarts().get(id).getProducts().isEmpty()) {
            cartRepository.getCarts().get(id).getProducts().clear();
            cartRepository.getCarts().get(id).setSum(new BigDecimal("0.0"));
        } else {
            throw new NotFoundException("Cart with ID #" + id + " is empty");
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
    public Cart getCartById(Integer id) throws NotFoundException {
        if (cartRepository.getCarts().containsKey(id)) {
            return cartRepository.getCarts().get(id);
        } else {
            throw new NotFoundException("Cart with ID #" + id + " is not found");
        }
    }

    @Override
    public String removeCartById(Integer id) throws NotFoundException {
        if (cartRepository.getCarts().containsKey(id)) {
            return cartRepository.getCarts().remove(id).toString();
        } else {
            throw new NotFoundException("Cart with ID #" + id + " is not found");
        }
    }
}