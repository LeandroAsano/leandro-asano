package leandroasano.api.Services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import leandroasano.api.Models.Post;
import leandroasano.api.Models.Reserve;
import leandroasano.api.Models.Sale;
import leandroasano.api.Models.User;
import leandroasano.api.Repositorys.PostRepository;
import leandroasano.api.Repositorys.ReserveRepository;
import leandroasano.api.Repositorys.SaleRepository;
import leandroasano.api.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    SaleRepository saleRepository;

    @Autowired
    ReserveRepository reserveRepository;

    @Autowired
    PostRepository postRepository;

    public void makeSale(int idreserve){
        Reserve reserve = reserveRepository.findByidReserve(idreserve);
        reserve.getPostres().setStock(reserve.getPostres().getStock()-1);
        if (reserve.getPostres().getStock()==0){
            reserve.getPostres().setState("Sold");
        }

        Sale sale = new Sale();
        sale.setSaledate(LocalDate.now());
        sale.setReserve(reserve);

        reserveRepository.save(reserve);
        saleRepository.save(sale);
    }

    public List<Sale> showAllSalesOfPosts(){
        System.out.println(saleRepository.findAll());
        return  saleRepository.findAll();
    }

    public void deleleteReserve(int idsale){
       saleRepository.delete(saleRepository.findByidsale(idsale));
    }


}
