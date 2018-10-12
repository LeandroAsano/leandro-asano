package leandroasano.api.Repositorys;

import leandroasano.api.Models.Reserve;
import leandroasano.api.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve,Integer> {

    Reserve findById(int id);

    List<Reserve> findAllByuserres(User user);



}
