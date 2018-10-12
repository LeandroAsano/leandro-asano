package leandroasano.api.Repositorys;

import leandroasano.api.Models.User;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findByfirstname(String firstname);

    List<User> findBylastname(String lastname);

    List<User> findByusername(String username);

    List<User> findBydateofbirth(LocalDate date);

    List<User> findByemail(String email);




}
