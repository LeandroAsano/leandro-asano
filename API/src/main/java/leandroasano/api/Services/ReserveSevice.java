package leandroasano.api.Services;


import leandroasano.api.Models.Post;
import leandroasano.api.Models.Reserve;
import leandroasano.api.Models.User;
import leandroasano.api.Repositorys.PostRepository;
import leandroasano.api.Repositorys.ReserveRepository;
import leandroasano.api.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReserveSevice {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ReserveRepository reserveRepository;

    public void makeReserve(int idpost, int idcurrentuser){
        Reserve reserve = new Reserve();
        Post posttoreserve = postRepository.findByidpost(idpost);
        User currentuser = userRepository.findByiduser(idcurrentuser);

        reserve.setDatereserve(LocalDate.now());
        reserve.setPostres(posttoreserve);
        reserve.setUserres(currentuser);

        posttoreserve.getReserve().add(reserve);
        reserveRepository.save(reserve);
    }

    public List<Reserve> showReservesOfUser(String username){
        User user = userRepository.findByusername(username);
        return  reserveRepository.findAllByuserres(user);
    }

    public List<Reserve> showMyReserves(int iduser){
        User user = userRepository.findByiduser(iduser);
        return  reserveRepository.findAllByuserres(user);
    }

    public void unamarkAllProductsReservations() throws Exception {
        for (Reserve reserve: reserveRepository.findAll()) {
            if (Period.between(LocalDate.now(),reserve.getDatereserve()).getDays()>7){
                reserveRepository.delete(reserve);
            }
        }
    }

    public void deleleteReserve(int idreserve){

        Reserve reservetodelete = reserveRepository.findByidReserve(idreserve);
        reserveRepository.delete(reservetodelete);

    }



}
