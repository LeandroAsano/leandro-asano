package leandroasano.api.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.Hibernate;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Sales")
public class Sale  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsale")
    private int idsale;

    @Column(name = "saledate")
    private LocalDate saledate;


    @OneToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "idres")
    @JsonIgnore
    private Reserve reserve;

    public Sale() {
    }

    public Sale(int idsale, LocalDate saledate, Reserve reserve) {
        this.idsale = idsale;
        this.saledate = LocalDate.now();
        this.reserve = reserve;
    }

    public int getIdsale() {
        return idsale;
    }

    public void setIdsale(int idsale) {
        this.idsale = idsale;
    }

    public LocalDate getSaledate() {
        return saledate;
    }

    public void setSaledate(LocalDate saledate) {
        this.saledate = saledate;
    }

    public Reserve getReserve() {
        return reserve;
    }

    public void setReserve(Reserve reserve) {
        this.reserve = reserve;
    }
}
