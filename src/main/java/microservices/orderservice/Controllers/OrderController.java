package microservices.orderservice.Controllers;

import lombok.RequiredArgsConstructor;
import microservices.orderservice.DTOs.Requests.OrderRequest;
import microservices.orderservice.Services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/microservices/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Object> placeOrder(@RequestBody OrderRequest orderRequest) {
        try {
            orderService.placeOrder(orderRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Order placed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(e.getMessage());
        }
    }
}

