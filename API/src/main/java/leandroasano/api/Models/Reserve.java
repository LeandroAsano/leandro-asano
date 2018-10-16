package leandroasano.api.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Reserves")
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idres")
    private int idReserve;

    @Column(name = "datereserve")
    private LocalDate datereserve;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iduser")
    private User userres;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idpost")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Post postres;

    @OneToOne(mappedBy = "reserve", cascade = CascadeType.ALL)
    @JsonIgnore
    private Sale sale;

    public Reserve() {
    }

    public Reserve(int idReserve) {
        this.idReserve = idReserve;
        this.datereserve = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Reserve{" +
                "idReserve=" + idReserve +
                ", datereserve=" + datereserve +
                ", userres=" + userres +
                ", postres=" + postres +
                ", sale=" + sale +
                '}';
    }

    public Post getPostres() {
        return postres;
    }

    public void setPostres(Post postres) {
        this.postres = postres;
    }

    public int getIdReserve() {
        return idReserve;
    }

    public void setIdReserve(int idReserve) {
        this.idReserve = idReserve;
    }

    public LocalDate getDatereserve() {
        return datereserve;
    }

    public void setDatereserve(LocalDate datereserve) {
        this.datereserve = datereserve;
    }

    public User getUserres() {
        return userres;
    }

    public void setUserres(User userres) {
        this.userres = userres;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
