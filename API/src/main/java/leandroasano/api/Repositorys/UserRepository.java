package leandroasano.api.Repositorys;

import leandroasano.api.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByiduser(int iduser);

    List<User> findByfirstname(String firstname);

    List<User> findBylastname(String lastname);

    User findByusername(String username);

    List<User> findBydateofbirth(LocalDate localDate);

    User findByemail(String email);




}
