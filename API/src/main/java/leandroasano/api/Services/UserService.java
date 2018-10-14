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
    PostRepository postRepository;

    @Autowired
    ReserveRepository reserveRepository;

    @Autowired
    HttpSession session;

    public void createUser(User user) throws Exception {
        if (session.getAttribute("role")=="admin"){
            Rol usr = new Rol();
            usr.setRol("user");
            user.getRols().add(usr);

            if (userRepository.findByusername(user.getUsername()).getUsername().equals(user.getUsername())){
                System.out.println("Username already exists!");
            } else{
                userRepository.save(user);
            }
           
        } else {
            throw new Exception("Error of permissions!");
        }
    }

    public void createAdmin(User user){
        Rol usr = new Rol();
        usr.setRol("user");
        Rol adm = new Rol();
        adm.setRol("admin");

        user.getRols().add(usr);
        user.getRols().add(adm);

        userRepository.save(user);
    }

    public List<Post> readUser(String username) throws Exception {
        if (session.getAttribute("role")=="admin") {
            User user = userRepository.findByusername(username);
            return postRepository.findAllByuserpost(user);
        } else {
            throw new Exception("Error of permissions");
        }
    }

    public void editUserDataAsAdmin(String username, User usertoupdate) throws Exception {
        if (session.getAttribute("role")=="admin"){
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
        } else {
            throw new Exception("Error of permissions!");
        }

    }

    public void editCurrentUser(User usertoupdate){

        int idcurrentuser = (int) session.getAttribute("iduser");

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
        if (session.getAttribute("role")=="admin"){
            userRepository.delete(user);
        } else{
            throw new Exception("Error of permissions");
        }

    }

    public List<User> getInactiveUsers(){

        List<User> users = new ArrayList<>();

        for (Reserve reserve: reserveRepository.findAll()) {
            if (Period.between(LocalDate.now(),reserve.getDatereserve()).getDays()>30){
                users.add(reserve.getUserres());
            } else if (Period.between(LocalDate.now(),reserve.getSale().getSaledate()).getDays()>30) {
                    users.add(reserve.getUserres());
            }

        }
        for (Post post: postRepository.findAll()) {
            if (Period.between(LocalDate.now(),post.getDateofpost()).getDays()>30){
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

    public List<User> findAllByDate(LocalDate date){ // NO OLVIDAR HACER
        return userRepository.findBydateofbirth(date);
    }

    public User findByEmail(String email){
        return  userRepository.findByemail(email);
    }




}