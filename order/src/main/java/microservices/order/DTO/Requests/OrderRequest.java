package microservices.order.DTO.Requests;

import jakarta.persistence.*;
import lombok.*;
import microservices.order.Models.OrderLineItem;

import java.util.List;

public class OrderRequest {

    private List<OrderLineItemRequest> orderLineItemRequests;

    public OrderRequest() {
    }

    public OrderRequest(List<OrderLineItemRequest> orderLineItemRequests) {
        this.orderLineItemRequests = orderLineItemRequests;
    }

    public void setOrderLineItemRequests(List<OrderLineItemRequest> orderLineItemRequests) {
        this.orderLineItemRequests = orderLineItemRequests;
    }

    public List<OrderLineItemRequest> getOrderLineItemRequests() {
        return orderLineItemRequests;
    }


}
