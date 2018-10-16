package leandroasano.api.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idprod")
    private int idprod;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "imageurl")
    private String imageurl;

    @Column(name = "price")
    private float price;

    @Column(name = "used")
    private Boolean used;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "idpost")
    @JsonIgnore
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "idprodcat")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ProductCategory category;

    public Product() {
    }

    public Product(String name, String description, String imageurl, float price, Boolean used,ProductCategory category) {
        this.name = name;
        this.description = description;
        this.imageurl = imageurl;
        this.price = price;
        this.used = used;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idprod=" + idprod +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", price=" + price +
                ", used=" + used +
                ", post=" + post +
                ", category=" + category +
                '}';
    }

    public int getidprod() {
        return idprod;
    }

    public void setidprod(int iprod) {
        this.idprod = iprod;
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

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
}