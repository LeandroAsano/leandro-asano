package leandroasano.api.Controllers;

import leandroasano.api.Models.Product;
import leandroasano.api.Models.User;
import leandroasano.api.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    HttpSession httpSession;

    @PostMapping(name = "/add")
    public ResponseEntity addUser( @RequestBody User user) throws Exception{
        try {
            userService.createUser(user);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e){
            throw new Exception("Error adding user");
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> showAllUsers() throws Exception {
        try{
            return new ResponseEntity<>(userService.showAll(),HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }

    }








}
