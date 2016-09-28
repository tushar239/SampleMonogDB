package withspringboot.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Tushar Chokshi @ 4/12/15.
 */
@Document(collection = "productattributes")
public class ProductAttributes extends Auditable {

    @Id
    private String id;

    private String color;
    private String weight;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }


    @Override
    public String toString() {
        return "ProductAttributes{" +
                "id='" + id + '\'' +
                ", color='" + color + '\'' +
                ", weight='" + weight +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", createdBy=" + createdBy +
                ", modifiedBy=" + modifiedBy +
                '}';
    }
}
