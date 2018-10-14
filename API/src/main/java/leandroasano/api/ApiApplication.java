package leandroasano.api;

import leandroasano.api.Models.Rol;
import leandroasano.api.Models.User;
import leandroasano.api.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class ApiApplication {

	@Autowired
	static UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
