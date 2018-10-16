package leandroasano.api.Controllers;

import leandroasano.api.Models.Rol;
import leandroasano.api.Models.User;
import leandroasano.api.Services.RolService;
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

    @Autowired
    RolService rolService;

    @PostMapping(value = "")
    public String login(HttpServletRequest request, @RequestParam(value = "username") String username, @RequestParam(value = "pass") String pass)throws Exception {

        session = request.getSession();

        User user = userService.findByUsername(username);
        if(Objects.nonNull(user))
            if(user.getPass().equals(pass)) {
                session.setAttribute("iduser", user.getIduser());
                Rol admin = rolService.obtainAdminRol();
                if (user.getRols().contains(admin)){
                    session.setAttribute("role", admin.getRol());
                } else {
                    session.setAttribute("role", "user");
                }
                return "User logged!";
            }
            else{
                return"Invalid Password";
            }
        else{
            throw  new Exception("Not Registered user");
        }

    }

    @PutMapping(value = "")
    public ResponseEntity<String> logout(HttpServletRequest request) throws Exception {
        session = request.getSession();
        if(Objects.nonNull(session.getAttribute("iduser"))){
            session.setAttribute("iduser", null);
            return new ResponseEntity("User Logout!",HttpStatus.OK);
        }
        else{
            throw  new Exception("Not logged user");
        }

    }


}
