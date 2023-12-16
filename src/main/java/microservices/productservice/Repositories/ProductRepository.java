package microservices.productservice.Repositories;

import microservices.productservice.Models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
