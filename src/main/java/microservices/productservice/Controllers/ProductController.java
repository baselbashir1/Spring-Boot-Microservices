package microservices.productservice.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import microservices.productservice.DTOs.Requests.ProductRequest;
import microservices.productservice.DTOs.Responses.ProductResponse;
import microservices.productservice.Services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/microservices/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/createProduct")
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        try {
            productService.createProduct(productRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(e.getMessage());
        }
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

}
