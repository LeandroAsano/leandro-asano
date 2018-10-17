package leandroasano.api.Services;


import leandroasano.api.Models.Post;
import leandroasano.api.Models.Reserve;
import leandroasano.api.Models.User;
import leandroasano.api.Repositorys.PostRepository;
import leandroasano.api.Repositorys.ReserveRepository;
import leandroasano.api.Repositorys.UserRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class ReserveSevice {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ReserveRepository reserveRepository;

    public void makeReserve(int idpost, int idcurrentuser, LocalDate date){
        Reserve reserve = new Reserve();
        Post posttoreserve = postRepository.findByidpost(idpost);
        User currentuser = userRepository.findByiduser(idcurrentuser);

        reserve.setDatereserve(date);
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
        List<Reserve> reserves = reserveRepository.findBydatereserveBefore(LocalDate.now().minusDays(7));
        for (Reserve reserve: reserves) {
                reserveRepository.delete(reserve);
        }
    }

    public void deleleteReserve(int idreserve){

        Reserve reservetodelete = reserveRepository.findByidReserve(idreserve);
        reserveRepository.delete(reservetodelete);

    }



}
