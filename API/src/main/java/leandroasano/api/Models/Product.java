package leandroasano.api.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "idprod")
    private int iprod;

    @Column(name = "Name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "imageurl")
    private String imageurl;

    @Column(name = "price")
    private float price;

    @Column(name = "used")
    private Boolean used;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idpost")
    @JsonIgnore
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idprodcat")
    @JsonIgnore
    private ProductCategory category;

    public Product() {
    }

    public Product(int iprod, String name, String description, String imageurl, float price, Boolean used) {
        this.iprod = iprod;
        this.name = name;
        this.description = description;
        this.imageurl = imageurl;
        this.price = price;
        this.used = used;
    }

    @Override
    public String toString() {
        return "Product{" +
                "iprod=" + iprod +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", price=" + price +
                ", used=" + used +
                ", post=" + post +
                ", category=" + category +
                '}';
    }

    public int getIprod() {
        return iprod;
    }

    public void setIprod(int iprod) {
        this.iprod = iprod;
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