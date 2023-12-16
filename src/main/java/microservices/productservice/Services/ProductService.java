package microservices.productservice.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.productservice.DTOs.Requests.ProductRequest;
import microservices.productservice.DTOs.Responses.ProductResponse;
import microservices.productservice.Models.Product;
import microservices.productservice.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        validateProductRequest(productRequest);
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product {} saved successfully", product.getId());
    }

    private void validateProductRequest(ProductRequest productRequest) {
        if (productRequest.getName() == null || productRequest.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (productRequest.getDescription() == null || productRequest.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Product description cannot be empty");
        }
        if (productRequest.getPrice() == null) {
            throw new IllegalArgumentException("Product price cannot be empty");
        }
        if (productRequest.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be a positive value");
        }
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();

        return productResponse;
    }
}
