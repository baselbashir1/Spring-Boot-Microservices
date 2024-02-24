package microservices.order.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number")
    private String orderNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItem> orderLineItems;

    public Order() {
    }

    public Order(String orderNumber, List<OrderLineItem> orderLineItems) {
        this.orderNumber = orderNumber;
        this.orderLineItems = orderLineItems;
    }

    public Long getId() {
        return this.id;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {
        return this.orderNumber;
    }

    public void setOrderLineItems(List<OrderLineItem> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }

    public List<OrderLineItem> getOrderLineItems() {
        return this.orderLineItems;
    }
}
