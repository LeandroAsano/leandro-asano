package leandroasano.api.Services;


import leandroasano.api.Models.Post;
import leandroasano.api.Models.Reserve;
import leandroasano.api.Repositorys.PostRepository;
import leandroasano.api.Repositorys.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReserveSevice {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ReserveRepository reserveRepository;

    public void makeReserve(int idpost, Reserve reserve){

        Post posttoreserve = postRepository.findByidpost(idpost);

        posttoreserve.getReserve().add(reserve);

    }

    public void deleleteReserve(int idreserve){

        Reserve reservetodelete = reserveRepository.findById(idreserve);

        reserveRepository.delete(reservetodelete);

    }



}
