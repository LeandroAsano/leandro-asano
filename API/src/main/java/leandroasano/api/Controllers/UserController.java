package leandroasano.api.Controllers;

import leandroasano.api.Models.Post;
import leandroasano.api.Models.Product;
import leandroasano.api.Models.ProductCategory;
import leandroasano.api.Models.User;
import leandroasano.api.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/users/add")
    public ResponseEntity addUser( @RequestBody User user) throws Exception{
        try {
            userService.createUser(user);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e){
            throw new Exception("Error adding user");
        }
    }

    @PostMapping("/admin/add")
    public ResponseEntity addAdmin( @RequestBody User user) throws Exception{
        try {
            userService.createAdmin(user);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e){
            throw new Exception("Error adding admin");
        }
    }

    @PutMapping("/users/update")
    public ResponseEntity updateUser(@RequestBody User user) throws Exception {
        try{
            userService.editCurrentUser(user);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }
    }

    @PutMapping("/admin/updateuser/{username}")
    public ResponseEntity updateUserAsAdmin(@PathVariable("username") String username ,@RequestBody User user) throws Exception {
        try {
            userService.editUserDataAsAdmin(username,user);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("Error in service");
        }
    }

    @GetMapping("/admin/read/{username}")
    public ResponseEntity<List<Post>> getPostsOfUser(@PathVariable("username") String username) throws Exception {
        try{
            return new ResponseEntity<>(userService.readUser(username),HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }

    }

    @GetMapping("/users/all")
    public ResponseEntity<List<User>> showAllUsers() throws Exception {
        try{
            return new ResponseEntity<>(userService.showAll(),HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }

    }

    @GetMapping("/users/all/inactive")
    public ResponseEntity<List<User>> getAllUsersInactives() throws Exception{
        try {
            return new ResponseEntity<>(userService.getInactiveUsers(),HttpStatus.OK);
        } catch (Exception e){
            throw new Exception("Error of service");
        }
    }

    @GetMapping("/users/id/{id}")
    public ResponseEntity<User> showUsersById(@PathVariable("id") int iduser) throws Exception {
        try{
            return new ResponseEntity<>(userService.findUserbyuserid(iduser),HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }

    }

    @GetMapping("/users/firstname/{firstname}")
    public ResponseEntity<List<User>> showUsersbyFirstname(@PathVariable("firstname") String firstname) throws Exception {
        try{
            return new ResponseEntity<>(userService.findAllByFirstName(firstname),HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }
    }

    @GetMapping("/users/lastname/{lastname}")
    public ResponseEntity<List<User>> showUsersbyLastname(@PathVariable("lastname") String lastname) throws Exception {
        try{
            return new ResponseEntity<>(userService.findAllByLastName(lastname),HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }
    }

    @GetMapping("/users/email/{email}")
    public ResponseEntity<User> showUsersbyEmail(@PathVariable("email") String email) throws Exception {
        try{
            return new ResponseEntity<>(userService.findByEmail(email),HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }
    }

    @GetMapping("/users/dateofbirth/{date}")
    public ResponseEntity<List<User>> showUsersbyDateofbirth(@PathVariable("date") LocalDate date) throws Exception {
        try{
            return new ResponseEntity<>(userService.findAllByDate(date),HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }
    }

    @DeleteMapping("/users/delete/{username}")
    public ResponseEntity deleteUser(@PathVariable("username") String username) throws Exception {
        try{
            User user = userService.findByUsername(username);
            userService.deleteUser(user);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }
    }





}
