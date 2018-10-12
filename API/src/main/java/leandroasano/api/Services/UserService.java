package leandroasano.api.Services;

import leandroasano.api.Models.*;
import leandroasano.api.Repositorys.PostRepository;
import leandroasano.api.Repositorys.ProductRepository;
import leandroasano.api.Repositorys.ReserveRepository;
import leandroasano.api.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ReserveRepository reserveRepository;

    @Autowired
    HttpSession session;

    public void createUser(User user) {
        userRepository.save(user);
    }

    public List<Post> readUser(User user){
        return postRepository.findAllByuserpost(user);
    }

    public void editUserData(int iduser, User usertoupdate){

        User user = userRepository.getOne(iduser);

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
        userRepository.save(user);
    }

    public void deleteUser(int id) {
        User usertodelete = userRepository.getOne(id);
        userRepository.delete(usertodelete);
    }

    public String getDetailProduct(int idprod){

        Product productFound = productRepository.findById(idprod);

        return productFound.getDescription();
    }


    public void markProductAsSold(int idpost){

        Post postformark = postRepository.findByidpost(idpost);

        if (postformark.getStock()==0){
            postformark.setState("Sold");
        } else {
            System.out.println("Error. Still have products in the post!");
        }
    }

    public void unamarkAllProductsReservations(){

        List<Reserve> reservations = reserveRepository.findAll();

        for (Reserve reserve: reservations) {
            if (Period.between(LocalDate.now(),reserve.getDatereserve()).getDays()>7){
                reserveRepository.delete(reserve);
            }
        }
    }


    public List<User> listInactiveUsers(User user){

        List<User> users = new ArrayList<>();

        for (Reserve reserve: reserveRepository.findAllByuserres(user)) {
            if (Period.between(LocalDate.now(),reserve.getDatereserve()).getDays()>30){
                users.add(user);
            } else if (Period.between(LocalDate.now(),reserve.getSale().getSaledate()).getDays()>30) {
                    users.add(user);
            }

        }
        for (Post post: postRepository.findAllByuserpost(user)) {
            if (Period.between(LocalDate.now(),post.getDateofpost()).getDays()>30){
                users.add(user);
            }
        }

        return users;
    }

    public List<User> showAll(){
        return userRepository.findAll();
    }

    //NOSE SI VA UNA BUSQUEDA POR ID

    public List<User>  listByFirstName(String firstname){
        return userRepository.findByfirstname(firstname);
    }

    public List<User> listByLastName (String lastname){
        return userRepository.findBylastname(lastname);
    }

    public User ListByUsername(String username){
        List<User> usernameList = userRepository.findByusername(username);
        if (!usernameList.isEmpty()){
            return usernameList.get(0);
        } else{
            return null;
        }
    }

    public List<User> listByDate(LocalDate date){ // NO OLVIDAR HACER
        return userRepository.findBydateofbirth(date);
    }

    public User listByEmail(String email){
        List<User> emailList = userRepository.findByemail(email);
        if (!emailList.isEmpty()){
            return emailList.get(0);
        } else {
            return null;
        }
    }




}