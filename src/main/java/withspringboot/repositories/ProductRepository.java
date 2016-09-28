package withspringboot.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import withspringboot.documents.Product;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String > {
    public List< Product > findBySku(String sku);

    @Query(value = "{sku: ?0, availability : 1}")
    public List < Product > findBySkuOnlyAvailables(String sku);
}