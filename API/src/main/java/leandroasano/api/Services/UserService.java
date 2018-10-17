package leandroasano.api.Services;

import leandroasano.api.Models.*;
import leandroasano.api.Repositorys.PostRepository;
import leandroasano.api.Repositorys.ReserveRepository;
import leandroasano.api.Repositorys.SaleRepository;
import leandroasano.api.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ReserveRepository reserveRepository;

    @Autowired
    SaleRepository saleRepository;

    public void createUser( User user, Rol usr) throws Exception {
        if (Objects.nonNull(userRepository.findByusername(user.getUsername()))) {
            throw new Exception("User already exists!");
        }else if (Objects.nonNull(userRepository.findByemail(user.getEmail()))){
            throw new Exception("User already exists!");
        } else {
            usr.getUserrol().add(user);
            user.getRols().add(usr);
            userRepository.save(user);
        }
    }

    public void createAdmin(User user, Rol usr, Rol adm) throws Exception {
        if (Objects.nonNull(userRepository.findByusername(user.getUsername()))) {
            throw new Exception("User already exists!");
        }else if (Objects.nonNull(userRepository.findByemail(user.getEmail()))){
            throw new Exception("User already exists!");
        } else {
            usr.getUserrol().add(user);
            adm.getUserrol().add(user);
            user.getRols().add(usr);
            user.getRols().add(adm);
            userRepository.save(user);
        }
    }

    public List<Post> readUser(String username) throws Exception {
        User user = userRepository.findByusername(username);
        return postRepository.findAllByuserpost(user);
    }

    public void editUserDataAsAdmin(String username, User usertoupdate) throws Exception {
        User user = userRepository.findByusername(username);
        if (Objects.nonNull(usertoupdate.getFirstname())){
            user.setFirstname(usertoupdate.getFirstname());
        }
        if (Objects.nonNull(usertoupdate.getLastname())){
            user.setLastname(usertoupdate.getLastname());
        }
        if (Objects.nonNull(usertoupdate.getUsername())){
            user.setUsername(usertoupdate.getUsername());
        }
        if (Objects.nonNull(usertoupdate.getEmail())){
            user.setEmail(usertoupdate.getEmail());
        }
        if (Objects.nonNull(usertoupdate.getDateofbirth())) {
            user.setDateofbirth(usertoupdate.getDateofbirth());
        }
        if (Objects.nonNull(usertoupdate.getPass())) {
            user.setPass(usertoupdate.getPass());
        }
        userRepository.save(user);

    }

    public void editCurrentUser(User usertoupdate, int idcurrentuser){

        User user = userRepository.getOne(idcurrentuser);

        if (Objects.nonNull(usertoupdate.getFirstname())){
            user.setFirstname(usertoupdate.getFirstname());
        }
        if (Objects.nonNull(usertoupdate.getLastname())){
            user.setLastname(usertoupdate.getLastname());
        }
        if (Objects.nonNull(usertoupdate.getUsername())){
            user.setUsername(usertoupdate.getUsername());
        }
        if (Objects.nonNull(usertoupdate.getEmail())){
            user.setEmail(usertoupdate.getEmail());
        }
        if (Objects.nonNull(usertoupdate.getDateofbirth())) {
            user.setDateofbirth(usertoupdate.getDateofbirth());
        }
        if (Objects.nonNull(usertoupdate.getPass())) {
            user.setPass(usertoupdate.getPass());
        }
        userRepository.save(user);
    }

    public void deleteUser(User user) throws Exception {
        userRepository.delete(user);

    }

    public List<User> getInactiveUsers(){
        List<User> users = new ArrayList<>();
        List<Post> posts = postRepository.findBydateofpostBefore(LocalDate.now().minusDays(30));
        List<Reserve> reserves = reserveRepository.findBydatereserveBefore(LocalDate.now().minusDays(30));
        List<Sale> sales = saleRepository.findBysaledateBefore(LocalDate.now().minusDays(30));

        for (Sale sale: sales) {
            if (!users.contains(sale.getReserve().getUserres())){
                users.add(sale.getReserve().getUserres());
            }
        }
        for (Reserve reserve: reserves) {
            if (!users.contains(reserve.getUserres())){
                users.add(reserve.getUserres());
            }
        }
        for (Post post: posts) {
            if (!users.contains(post.getUserpost())){
                users.add(post.getUserpost());
            }
        }
        return users;
    }

    public List<User> showAll(){
        return userRepository.findAll();
    }

    public User findUserbyuserid(int iduser){
        return userRepository.findByiduser(iduser);
    }

    public List<User>  findAllByFirstName(String firstname){
        return userRepository.findByfirstname(firstname);
    }

    public List<User> findAllByLastName (String lastname){
        return userRepository.findBylastname(lastname);
    }

    public User findByUsername(String username){
        return userRepository.findByusername(username);
    }

    public List<User> findAllByDate(LocalDate date){ // NO OLVIDAR HACER EL ORDEN
        return userRepository.findBydateofbirth(date);
    }

    public User findByEmail(String email){
        return  userRepository.findByemail(email);
    }




}