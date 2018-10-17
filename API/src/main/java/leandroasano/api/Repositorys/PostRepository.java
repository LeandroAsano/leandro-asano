package leandroasano.api.Repositorys;

import leandroasano.api.Models.Post;
import leandroasano.api.Models.User;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

    Post findByidpost(int idpost);

    List<Post> findBydateofpostBefore(LocalDate localDate);

    List<Post> findAllByuserpost(User user);



}
