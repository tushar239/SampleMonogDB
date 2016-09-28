package withspringboot.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/*
http://docs.spring.io/spring-data/mongodb/docs/1.3.3.RELEASE/reference/html/mapping-chapter.html

MappingMongoConverter
---------------------
Rich mapping support is provided by the MappingMongoConverter. MongoMappingConverter has a rich metadata model that provides a full feature set of functionality to map domain objects to MongoDB documents.The mapping metadata model is populated using annotations on your domain objects. However, the infrastructure is not limited to using annotations as the only source of metadata information. The MongoMappingConverter also allows you to map objects to documents without providing any additional metadata, by following a set of conventions.
In this section we will describe the features of the MongoMappingConverter. How to use conventions for mapping objects to documents and how to override those conventions with annotation based mapping metadata.

All possible Mongo annotations
------------------------------
@Id - applied at the field level to mark the field used for identiy purpose.
@Document - applied at the class level to indicate this class is a candidate for mapping to the database. You can specify the name of the collection where the database will be stored.
@DBRef - applied at the field to indicate it is to be stored using a com.mongodb.DBRef.
@Indexed - applied at the field level to describe how to index the field.
@CompoundIndex - applied at the type level to declare Compound Indexes
@GeoSpatialIndexed - applied at the field level to describe how to geoindex the field.
@Transient - by default all private fields are mapped to the document, this annotation excludes the field where it is applied from being stored in the database
@PersistenceConstructor - marks a given constructor - even a package protected one - to use when instantiating the object from the database. Constructor arguments are mapped by name to the key values in the retrieved DBObject.
@Value - this annotation is part of the Spring Framework . Within the mapping framework it can be applied to constructor arguments. This lets you use a Spring Expression Language statement to transform a key's value retrieved in the database before it is used to construct a domain object. In order to reference a property of a given document one has to use expressions like: @Value("#root.myProperty") where root refers to the root of the given document.
@Field - applied at the field level and described the name of the field as it will be represented in the MongoDB BSON document thus allowing the name to be different than the fieldname of the class.

@Document
@CompoundIndexes({
    @CompoundIndex(name = "age_idx", def = "{'lastName': 1, 'age': -1}")
})
public class Person<T extends Address> {

  @Id
  private String id;

  @Indexed(unique = true)
  private Integer ssn;

  @Field("fName")
  private String firstName;

  @Indexed
  private String lastName;

  private Integer age;

  @Transient
  private Integer accountTotal;

  @DBRef
  private List<Account> accounts;

  private T address;
 public Person(Integer ssn) {
    this.ssn = ssn;
  }

  @PersistenceConstructor
  public Person(Integer ssn, String firstName, String lastName, Integer age, T address) {
    this.ssn = ssn;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.address = address;
  }

  public String getId() {
    return id;
  }

 // no setter for Id.  (getter is only exposed for some unit testing)

  public Integer getSsn() {
    return ssn;
  }


// other getters/setters ommitted
}

Overriding Mapping with explicit Converters
-------------------------------------------
public class PersonReadConverter implements Converter<DBObject, Person> {

  public Person convert(DBObject source) {
    Person p = new Person((ObjectId) source.get("_id"), (String) source.get("name"));
    p.setAge((Integer) source.get("age"));
    return p;
  }

}
public class PersonWriteConverter implements Converter<Person, DBObject> {

  public DBObject convert(Person source) {
    DBObject dbo = new BasicDBObject();
    dbo.put("_id", source.getId());
    dbo.put("name", source.getFirstName());
    dbo.put("age", source.getAge());
    return dbo;
  }

}

*/
@Document(collection = "products")
public class Product extends Auditable {
    @Id
    private String id;

    private String sku;
    @Field(value = "material_name")
    private String materialName;
    private Double price;
    private Integer availability;

    @DBRef
    //@CascadeSave // At present not available with spring-data, so need to save ProductAttributes separately
    private ProductAttributes productAttributes;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public ProductAttributes getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(ProductAttributes productAttributes) {
        this.productAttributes = productAttributes;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", sku='" + sku + '\'' +
                ", materialName='" + materialName + '\'' +
                ", price=" + price +
                ", availability=" + availability +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", createdBy=" + createdBy +
                ", modifiedBy=" + modifiedBy +
                '}';
    }

    /*@Override
    public String getCurrentAuditor() {
        return "Tushar Chokshi";
    }
*/}
