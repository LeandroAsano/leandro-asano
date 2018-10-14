package leandroasano.api.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Sales")
public class Sale {

    @Id
    @GeneratedValue
    @Column(name = "idsale")
    private int idsale;

    @Column(name = "saledate")
    private LocalDate saledate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idres")
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
