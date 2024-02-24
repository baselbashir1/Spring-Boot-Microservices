package microservices.order.Services;

import microservices.order.DTO.Requests.OrderLineItemRequest;
import microservices.order.DTO.Requests.OrderRequest;
import microservices.order.Models.Order;
import microservices.order.Models.OrderLineItem;
import microservices.order.Repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private Logger logger = LoggerFactory.getLogger(OrderService.class.getName());

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemRequests()
                .stream().map(this::mapToDto).toList();

        order.setOrderLineItems(orderLineItems);
        orderRepository.save(order);
        logger.info("Order {} placed.", order.getId());
    }

    public OrderLineItem mapToDto(OrderLineItemRequest orderLineItemRequest) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setSkuCode(orderLineItemRequest.getSkuCode());
        orderLineItem.setPrice(orderLineItemRequest.getPrice());
        orderLineItem.setQuantity(orderLineItemRequest.getQuantity());
        return orderLineItem;
    }
}
