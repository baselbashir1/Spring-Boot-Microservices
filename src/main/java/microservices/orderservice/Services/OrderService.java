package microservices.orderservice.Services;

import lombok.RequiredArgsConstructor;
import microservices.orderservice.DTOs.Requests.OrderLineItemsRequest;
import microservices.orderservice.DTOs.Requests.OrderRequest;
import microservices.orderservice.Models.Order;
import microservices.orderservice.Models.OrderLineItems;
import microservices.orderservice.Repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsRequestList()
                .stream().map(this::mapToDTO)
                .toList();

        order.setOrderLineItemsList(orderLineItems);
        orderRepository.save(order);
    }

    private OrderLineItems mapToDTO(OrderLineItemsRequest orderLineItemsRequest) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsRequest.getSkuCode());
        orderLineItems.setPrice(orderLineItemsRequest.getPrice());
        orderLineItems.setQuantity(orderLineItemsRequest.getQuantity());
        return orderLineItems;
    }
}
