package leandroasano.api.Controllers;

import leandroasano.api.Models.*;
import leandroasano.api.Services.RolService;
import leandroasano.api.Services.UserService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RolService rolService;

    @Autowired
    HttpSession session;

    @PostMapping("/roles/create")
    public ResponseEntity createRoles() throws Exception{
        try {
            rolService.createRols();
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e){
            throw new Exception("Error adding roles");
        }
    }

    @PostMapping("/admin/users/add")
    public ResponseEntity addUser( @RequestBody User user) throws Exception{
        if (session.getAttribute("role").equals("admin")){
                Rol usr = rolService.obtainUserRol();
                userService.createUser(user,usr);
                return new ResponseEntity(HttpStatus.CREATED);

        } else {
            throw new Exception("Error of permissions!");
        }
    }

    @PostMapping("/admin/add")
    public ResponseEntity addAdmin( @RequestBody User user) throws Exception{
        try {
            Rol usr = rolService.obtainUserRol();
            Rol adm = rolService.obtainAdminRol();
            userService.createAdmin(user,usr,adm);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e){
            throw e;
        }
    }

    @PutMapping("/users/update")
    public ResponseEntity updateCurrentUser(@RequestBody User user) throws Exception {
        try{
            int idcurrentuser = (int) session.getAttribute("iduser");

            userService.editCurrentUser(user,idcurrentuser);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error of update");
        }
    }

    @PutMapping("/admin/updateuser/{username}")
    public ResponseEntity updateUserAsAdmin(@PathVariable("username") String username ,@RequestBody User user) throws Exception {
        if (session.getAttribute("role").equals("admin")){
            userService.editUserDataAsAdmin(username,user);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            throw new Exception("Error of permissions!");
        }
    }

    @GetMapping("/admin/read/{username}")   //show the posts of user
    public ResponseEntity<List<Post>> getPostsOfUser(@PathVariable("username") String username) throws Exception {
        if (session.getAttribute("role").equals("admin")) {
            return new ResponseEntity<>(userService.readUser(username),HttpStatus.OK);
        } else {
            throw new Exception("Error of permissions");
        }
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<User>> showAllUsers() throws Exception {
        try{
            return new ResponseEntity<>(userService.showAll(),HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error of service");
        }

    }

    @GetMapping("/admin/users/all/inactive")
    public ResponseEntity<List<User>> getAllUsersInactives() throws Exception{
        try {
            return new ResponseEntity<>(userService.getInactiveUsers(),HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception("Error of service");
        }
    }

    @GetMapping("/users/id/{id}")
    public ResponseEntity<User> showUsersById(@PathVariable("id") int iduser) throws Exception {
        try{
            return new ResponseEntity<>(userService.findUserbyuserid(iduser),HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error of service");
        }

    }

    @GetMapping("/users/firstname/{firstname}")
    public ResponseEntity<List<User>> showUsersbyFirstname(@PathVariable("firstname") String firstname) throws Exception {
        try{
            return new ResponseEntity<>(userService.findAllByFirstName(firstname),HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error of service");
        }
    }

    @GetMapping("/users/lastname/{lastname}")
    public ResponseEntity<List<User>> showUsersbyLastname(@PathVariable("lastname") String lastname) throws Exception {
        try{
            return new ResponseEntity<>(userService.findAllByLastName(lastname),HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error of service");
        }
    }

    @GetMapping("/users/email/{email}")
    public ResponseEntity<User> showUsersbyEmail(@PathVariable("email") String email) throws Exception {
        try{
            return new ResponseEntity<>(userService.findByEmail(email),HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error of service");
        }
    }

    @GetMapping("/users/dateofbirth/{date}")
    public ResponseEntity<List<User>> showUsersbyDateofbirth(@PathVariable("date") String date) throws Exception {
        try{
            LocalDate dateparsed = LocalDate.parse(date);
            return new ResponseEntity<>(userService.findAllByDate(dateparsed),HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error of service");
        }
    }

    @DeleteMapping("/admin/delete/{username}")
    public ResponseEntity deleteUser(@PathVariable("username") String username) throws Exception {
        if (session.getAttribute("role").equals("admin")){
            User user = userService.findByUsername(username);
            userService.deleteUser(user);
            return new ResponseEntity(HttpStatus.OK);
        } else{
            throw new Exception("Error of permissions");
        }
    }





}
