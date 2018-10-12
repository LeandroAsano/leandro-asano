package leandroasano.api.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ProductCategories")
public class ProductCategory {

    @Id
    @GeneratedValue
    @Column(name = "idprodcat")
    private int idprodcat;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public ProductCategory() {
    }

    public ProductCategory(int idprodcat, String name, String description) {
        this.idprodcat = idprodcat;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "idprodcat=" + idprodcat +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int getIdprodcat() {
        return idprodcat;
    }

    public void setIdprodcat(int idprodcat) {
        this.idprodcat = idprodcat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}