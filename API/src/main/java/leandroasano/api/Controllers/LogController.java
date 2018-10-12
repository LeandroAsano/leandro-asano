package leandroasano.api.Controllers;

import leandroasano.api.Models.User;
import leandroasano.api.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@RestController
@RequestMapping("/login")
public class LogController {

    @Autowired
    HttpSession session;

    @Autowired
    UserService userService;

    @PostMapping(value = "")
    public ResponseEntity login(HttpServletRequest request, HttpServletRequest response, @RequestBody User customer) throws Exception {

        session = request.getSession();

        User user = userService.ListByUsername(customer.getUsername());
        if(Objects.nonNull(user))
            if(user.getPass().equals(customer.getPass())) {
                session.setAttribute("iduser", user.getIduser());
                return new ResponseEntity(HttpStatus.OK);
            }
            else{
                throw  new  Exception("Invalid Password");
            }
        else{
            throw  new Exception("Not logged user");
        }

    }

    @PutMapping(value = "")
    public ResponseEntity logout(HttpServletRequest request,HttpServletRequest response) throws Exception {
        session = request.getSession();
        if(Objects.nonNull(session.getAttribute("iduser"))){
            session.setAttribute("iduser", null);
            return new ResponseEntity(HttpStatus.OK);
        }
        else{
            throw  new Exception("Not logged user");
        }

    }


}
