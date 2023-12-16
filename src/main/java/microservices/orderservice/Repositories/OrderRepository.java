package microservices.orderservice.Repositories;

import microservices.orderservice.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
