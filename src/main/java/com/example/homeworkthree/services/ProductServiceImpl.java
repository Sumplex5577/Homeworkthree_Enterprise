package com.example.homeworkthree.services;

import com.example.homeworkthree.exceptions.NotFoundException;
import com.example.homeworkthree.model.Product;
import com.example.homeworkthree.repositories.ProductRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private static final String FILE_PATH = "src/main/resources/products.csv";
    private final ProductRepository productRepository;
    private final CsvWriter<ProductCsvDto> csvWriter;

    public ProductServiceImpl(ProductRepository productRepository, CsvWriter<ProductCsvDto> csvWriter) {
        this.productRepository = productRepository;
        this.csvWriter = csvWriter;
    }

    @Override
    public String createProduct(@NonNull AddProductDto productDTO) {
        Product product = new Product(productDTO.getProductName(), productDTO.getPrice());
        productRepository.getProducts().put(product.getProductId(), product);
        csvWriter.write(FILE_PATH, new ProductCsvDto(product.getProductId(),
                product.getProductName(), product.getPrice(), "created"));
        return product.toString();
    }

    @Override
    public String removeProductById(Integer id) throws NotFoundException {
        if (productRepository.getProducts().containsKey(id)) {
            Product product = productRepository.getProducts().remove(id);
            csvWriter.write(FILE_PATH, new ProductCsvDto(product.getProductId(),
                    product.getProductName(), product.getPrice(), "deleted"));
            return product.toString();
        } else {
            throw new NotFoundException("Product with ID #" + id + " is not found");
        }
    }

    @Override
    public Collection<Product> getAllProducts() {
        return productRepository.getProducts().values();
    }

    @Override
    public Product getProductById(Integer id) throws NotFoundException {
        if (productRepository.getProducts().containsKey(id)) {
            return productRepository.getProducts().get(id);
        } else {
            throw new NotFoundException("Product with ID #" + id + " is not found");
        }
    }

    @Override
    public Product getProductByName(@NonNull GetProductDto dto) throws NotFoundException {
        Optional<Product> resultProduct = productRepository.getProducts().values().stream()
                .filter(product -> product.getProductName().equals(dto.getName())).findFirst();
        return resultProduct.orElseThrow(() -> new NotFoundException("Product with name "
                + dto.getName() + " is not found"));
    }

    @Override
    public String updateProductNameById(Integer id, @NonNull UpdateProductDto dto) throws NotFoundException {
        if (productRepository.getProducts().containsKey(id)) {
            Product product = productRepository.getProducts().get(id);
            product.setProductName(dto.getProductName());
            csvWriter.write(FILE_PATH, new ProductCsvDto(product.getProductId(),
                    product.getProductName(), product.getPrice(), "updated"));
            return product.toString();
        } else {
            throw new NotFoundException("Product with ID #" + id + " is not found");
        }
    }

    @Override
    public String updateProductPriceById(Integer id, @NonNull UpdateProductDto dto) throws NotFoundException {
        if (productRepository.getProducts().containsKey(id)) {
            Product product = productRepository.getProducts().get(id);
            product.setPrice(dto.getPrice());
            csvWriter.write(FILE_PATH, new ProductCsvDto(product.getProductId(),
                    product.getProductName(), product.getPrice(), "updated"));
            return product.toString();
        } else {
            throw new NotFoundException("Product with ID #" + id + " is not found");
        }
    }
}
