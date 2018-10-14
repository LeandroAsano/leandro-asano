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
    public String login(HttpServletRequest request, @RequestParam(value = "username") String username, @RequestParam(value = "pass") String pass)throws Exception {

        session = request.getSession();

        User user = userService.findByUsername(username);
        if(Objects.nonNull(user))
            if(user.getPass().equals(pass)) {
                session.setAttribute("iduser", user.getIduser());
                if (user.getRols().contains("admin")){
                    session.setAttribute("role", "admin");
                }else{
                    session.setAttribute("role", "user" );
                }
                return "User logged!";
            }
            else{
                return"Invalid Password";
            }
        else{
            throw  new Exception("Not logged user");
        }

    }

    @PutMapping(value = "")
    public ResponseEntity logout(HttpServletRequest request) throws Exception {
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
