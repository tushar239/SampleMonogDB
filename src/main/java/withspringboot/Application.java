package withspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import withspringboot.documents.Product;
import withspringboot.documents.ProductAttributes;

import java.util.List;

/*
http://docs.spring.io/spring-data/mongodb/docs/current/reference/html/
http://docs.spring.io/spring-data/mongodb/docs/1.3.3.RELEASE/reference/html/mapping-chapter.html
http://www.javacodegeeks.com/2014/05/rocking-with-mongodb-on-spring-boot.html
http://krams915.blogspot.com/2012/01/spring-mvc-31-implement-crud-with_7897.html

 */

@Configuration
@EnableMongoAuditing
@EnableAutoConfiguration
@ComponentScan
public class Application implements CommandLineRunner {

    @Autowired
    private ProductService productService;

    public void run(String... args) throws Exception {
        List<Product> products = findProduct();

        if (products == null || products.size() == 0) {
            createData();
        }

        List < Product > availableSkuCustom = productService.getAvailableSkuCustom("NEX.6");
        System.out.printf("result of availableSkuCustom is :" + availableSkuCustom);
    }

    private List< Product >  findProduct() {
        List< Product > products = productService.getSku("NEX.6");
        System.out.println("result of getSku is :" + products);
        return products;
    }
    private void createData() {
        Product product = new Product();
        product.setAvailability(1);
        product.setMaterialName("some material");
        product.setSku("NEX.6");
        product.setPrice(1000.15);

        ProductAttributes productAttributes = new ProductAttributes();
        productAttributes.setColor("red");
        productAttributes.setWeight("125 kg");
        product.setProductAttributes(productAttributes);

        // We must save both separately since there is no cascading feature in Spring Data MongoDB (for now)
        System.out.println("Inserting ProductAttributes and Product");
        productService.save(productAttributes);
        productService.save(product);
        /*
        This is how ProductAttributes and Product will be inserted in DB
        {
            "_id" : ObjectId("552b1f100364fcde925fd02f"),
            "_class" : "withspringboot.ProductAttributes",
            "color" : "red",
            "weight" : "125 kg"
        }

        {
             "_id" : ObjectId("552b1f100364fcde925fd030"),
             "_class" : "withspringboot.Product",
             "sku" : "NEX.6",
             "material_name" : "some material",
             "price" : 1000.15,
             "availability" : 1,
             "productAttributes" : DBRef("productattributes", ObjectId("552b1f100364fcde925fd02f"))
         }
         */
        findProduct();
    }
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
