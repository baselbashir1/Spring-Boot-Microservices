package microservices.productservice.DTOs.Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductRequest {

    @NotBlank(message = "The name cannot be null")
    private String name;

    @NotBlank(message = "The description cannot be null")
    private String description;

    @NotNull(message = "The price cannot be null")
    @Positive(message = "The price must be positive")
    private BigDecimal price;
}
