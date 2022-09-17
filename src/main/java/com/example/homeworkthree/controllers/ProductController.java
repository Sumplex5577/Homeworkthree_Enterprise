package com.example.homeworkthree.controllers;

import com.example.homeworkthree.exceptions.NotFoundException;
import com.example.homeworkthree.models.Product;
import com.example.homeworkthree.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


    @RestController
    @RequestMapping(path="/shop/product")
    public class ProductController {

        private final ProductService productService;

        public ProductController(ProductService productService) {
            this.productService = productService;
        }

        @PostMapping("/create")
        @ResponseStatus(HttpStatus.OK)
        public Product createProduct(@RequestBody Product product){
            return productService.createProduct(product);
        }

        @GetMapping()
        @ResponseStatus(HttpStatus.OK)
        public Product getProductById(@RequestParam Integer productId) throws NotFoundException {
            return productService.getProductById(productId);
        }

        @PutMapping("/update")
        @ResponseStatus(HttpStatus.OK)
        public Product updateProduct(@RequestParam Integer productId, @RequestBody Product product) throws NotFoundException {
            return productService.updateProduct(productId, product);
        }

        @DeleteMapping("/delete")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteProduct(@RequestParam Integer productId) throws NotFoundException {
            productService.deleteProduct(productId);
        }

        @GetMapping("/all")
        @ResponseStatus(HttpStatus.OK)
        public Collection<Product> getAll() {
            return productService.getAllProducts();
        }
    }

