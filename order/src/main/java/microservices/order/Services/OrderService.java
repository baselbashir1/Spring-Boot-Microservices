package microservices.order.Services;

import microservices.order.DTO.Requests.OrderLineItemRequest;
import microservices.order.DTO.Requests.OrderRequest;
import microservices.order.DTO.Responses.InventoryResponse;
import microservices.order.Models.Order;
import microservices.order.Models.OrderLineItem;
import microservices.order.Repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderService.class.getName());

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Transactional
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemRequests()
                .stream().map(this::mapToDto).toList();

        order.setOrderLineItems(orderLineItems);

        List<String> skuCodes = order.getOrderLineItems().stream()
                .map(OrderLineItem::getSkuCode)
                .toList();

        InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
                .allMatch(InventoryResponse::getIsInStock);

        if (allProductsInStock) {
            orderRepository.save(order);
            logger.info("Order {} placed.", order.getId());
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later.");
        }
    }

    public OrderLineItem mapToDto(OrderLineItemRequest orderLineItemRequest) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setSkuCode(orderLineItemRequest.getSkuCode());
        orderLineItem.setPrice(orderLineItemRequest.getPrice());
        orderLineItem.setQuantity(orderLineItemRequest.getQuantity());
        return orderLineItem;
    }
}
