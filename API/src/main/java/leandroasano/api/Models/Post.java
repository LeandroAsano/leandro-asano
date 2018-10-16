package leandroasano.api.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpost")
    private int idpost;

    @Column(name = "dateofpost")
    private LocalDate dateofpost;

    @Column(name = "state")
    private String state;

    @Column(name = "stock")
    private int stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iduser")
    @JsonIgnore
    private User userpost;

    @OneToOne(mappedBy = "post",cascade = CascadeType.ALL)
    private Product product;

    @OneToMany(mappedBy = "postres",cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Reserve> reserve = new ArrayList<>();


    public Post() {
    }

    public Post(int stock, Product product) {
        this.dateofpost = LocalDate.now();
        this.state = "In Sale";
        this.stock = stock;
        this.product = product;
    }

    @Override
    public String toString() {
        return "Post{" +
                "idpost=" + idpost +
                ", dateofpost=" + dateofpost +
                ", state='" + state + '\'' +
                ", stock=" + stock +
                ", userpost=" + userpost +
                ", product=" + product +
                ", reserve=" + reserve +
                '}';
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIdpost() {
        return idpost;
    }

    public void setIdpost(int idpost) {
        this.idpost = idpost;
    }

    public LocalDate getDateofpost() {
        return dateofpost;
    }

    public void setDateofpost(LocalDate dateofpost) {
        this.dateofpost = dateofpost;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public User getUserpost() {
        return userpost;
    }

    public void setUserpost(User userpost) {
        this.userpost = userpost;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Reserve> getReserve() {
        return reserve;
    }

    public void setReserve(List<Reserve> reserve) {
        this.reserve = reserve;
    }
}
