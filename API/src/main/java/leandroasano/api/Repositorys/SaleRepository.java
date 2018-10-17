package leandroasano.api.Repositorys;

import leandroasano.api.Models.Post;
import leandroasano.api.Models.Reserve;
import leandroasano.api.Models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Integer> {

    Sale findByidsale(int idsale);

    List<Sale> findBysaledateBefore(LocalDate localDate);

    List<Sale> findAll();

}
