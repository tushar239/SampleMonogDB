package withspringboot.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import withspringboot.documents.ProductAttributes;

/**
 * @author Tushar Chokshi @ 4/12/15.
 */
public interface ProductAttributesRepository extends MongoRepository<ProductAttributes, String> {
}
