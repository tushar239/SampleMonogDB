package withspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import withspringboot.documents.Product;
import withspringboot.documents.ProductAttributes;
import withspringboot.repositories.ProductAttributesRepository;
import withspringboot.repositories.ProductRepository;
import withspringboot.repositories.ProductRepositoryCustom;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductRepositoryCustom customRepository;

    @Autowired
    private ProductAttributesRepository productAttributesRepository;

    public List<Product> getSku(String sku){
        return repository.findBySku(sku);
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public ProductAttributes save(ProductAttributes productAttributes) {
        return productAttributesRepository.save(productAttributes);
    }

    public List < Product > getAvailableSkuCustom(String sku){
        return customRepository.findBySkuOnlyAvailablesCustom(sku);
    }
}