package withspringboot.repositories;

import withspringboot.documents.Product;

import java.util.List;

/**
 * @author Tushar Chokshi @ 4/12/15.
 */
public interface ProductRepositoryCustom {
    public List<Product> findBySkuOnlyAvailablesCustom(String sku);
}
