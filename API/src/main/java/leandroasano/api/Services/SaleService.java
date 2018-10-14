package leandroasano.api.Services;

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
        Sale sale = new Sale();
        sale.setReserve(reserve);
        saleRepository.save(sale);
    }

    public List<Sale> showAllSalesOfPosts(){

        return  saleRepository.findAll();
    }

    public void deleleteReserve(int idsale){

       saleRepository.findByidsale(idsale);

    }


}
