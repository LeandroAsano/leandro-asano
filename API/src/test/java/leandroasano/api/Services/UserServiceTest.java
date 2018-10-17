package leandroasano.api.Services;

import leandroasano.api.Models.Post;
import leandroasano.api.Models.Reserve;
import leandroasano.api.Models.Rol;
import leandroasano.api.Models.User;
import leandroasano.api.Repositorys.UserRepository;
import org.apache.tomcat.jni.Local;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class UserServiceTest {

    private static User user = new User();
    private static List<User> users;
    private static UserService userService;

    @BeforeAll
    public static void setUp(){
        userService = Mockito.mock(UserService.class);

        user.setIduser(1);
        user.setFirstname("Leo");
        user.setLastname("Carlos");
        user.setUsername("leandroasano");
        user.setPass("123");
        user.setDateofbirth(LocalDate.of(1996,4,26));
        user.setEmail("leandroasano@gmail.com");

        users = new ArrayList<>();
        users.add(user);

    }

    @Test
    void createUserTest() throws Exception {
        //arrange
        Rol usr = new Rol();
        usr.setRol("user");
        usr.setUserrol(new ArrayList<>());
        //act
        userService.createUser(user,usr);
        //assert
        Mockito.verify(userService).createUser(user,usr);
    }


    @Test
    void createAdminTest() throws Exception {
        //arrange
        Rol usr = new Rol();
        usr.setRol("user");
        usr.setUserrol(new ArrayList<>());
        Rol admin = new Rol();
        admin.setRol("admin");
        admin.setUserrol(new ArrayList<>());
        //act
        userService.createAdmin(user,usr,admin);
        //assert
        Mockito.verify(userService).createAdmin(user,usr,admin);
    }

    @Test
    void readUserTest() throws Exception {
        //arrange
        List<Post> posts = new ArrayList<>();
        Post post = Mockito.mock(Post.class);
        posts.add(post);
        user.setUserposts(posts);
        String username= "leandroasano";
        Mockito.when(userService.readUser(user.getUsername())).thenReturn(user.getUserposts());
        //act
        List<Post> foundposts = userService.readUser(username);
        //assert
        Assertions.assertNotNull(foundposts);
        Mockito.verify(userService).readUser(username);
}

    @Test
    void editUserDataAsAdmin() throws Exception {
        //arrange
        User user2 = new User();
        user2.setIduser(2);
        user2.setFirstname("Ale");
        user2.setLastname("Maxi");
        user2.setUsername("aleasano");
        user2.setPass("456");
        user2.setDateofbirth(LocalDate.of(1996,4,25));
        user2.setEmail("aleasano@gmail.com");
        User user3 = Mockito.mock(User.class);
        //act
        userService.editUserDataAsAdmin(user3.getUsername(),user2);
        //assert
        verify(userService).editUserDataAsAdmin(user3.getUsername(),user2);
    }

    @Test
    void editCurrentUserTest() {
        //arrange
        User user2 = new User();
        user2.setIduser(2);
        user2.setFirstname("Ale");
        user2.setLastname("Maxi");
        user2.setUsername("aleasano");
        user2.setPass("456");
        user2.setDateofbirth(LocalDate.of(1996,4,25));
        user2.setEmail("aleasano@gmail.com");
        int iduser = 1;
        //act
        userService.editCurrentUser(user2,iduser);
        //assert
        verify(userService).editCurrentUser(user2,iduser);
    }

    @Test
    void deleteUserTest() throws Exception {
        //arrange
        User user = Mockito.mock(User.class);
        //act
        userService.deleteUser(user);
        //assert
        Mockito.verify(userService).deleteUser(user);
    }

    @Test
    void WhenCallToMethodInactive_ThenCallWorks() {
        //arrange
        //act
        userService.getInactiveUsers();
        //
        Mockito.verify(userService).getInactiveUsers();
    }

    @Test
    void showAllTest() {
        //arrange
        User user2 = new User();
        user2.setIduser(2);
        user2.setFirstname("Ale");
        user2.setLastname("Maxi");
        user2.setUsername("aleasano");
        user2.setPass("456");
        user2.setDateofbirth(LocalDate.of(1996,4,25));
        user2.setEmail("aleasano@gmail.com");
        users.add(user2);
        Mockito.when(userService.showAll()).thenReturn(users);
        //act
        List<User> foundusers = userService.showAll();
        //assert
        Assertions.assertNotNull(foundusers);
        Mockito.verify(userService).showAll();
    }

    @Test
    void findUserbyuseridTest() {
        //arrange
        Mockito.when(userService.findUserbyuserid(user.getIduser())).thenReturn(user);
        int iduser = 1;
        //act
        User found = userService.findUserbyuserid(iduser);
        //assert
        Assertions.assertNotNull(found);
        Mockito.verify(userService).findUserbyuserid(iduser);
    }

    @Test
    void findAllByFirstNameTest() {
        //arrange
        Mockito.when(userService.findAllByFirstName(user.getFirstname())).thenReturn(users);
        String firstname = "Leo";
        //act
        List<User> foundusers = userService.findAllByFirstName(firstname);
        //assert
        Assertions.assertNotNull(foundusers);
        Mockito.verify(userService).findAllByFirstName(firstname);
    }

    @Test
    void findAllByLastNameTest() {
        //arrange
        Mockito.when(userService.findAllByLastName(user.getLastname())).thenReturn(users);
        String lastname = "Carlos";
        //act
        List<User> foundusers = userService.findAllByLastName(lastname);
        //assert
        Assertions.assertNotNull(foundusers);
        Mockito.verify(userService).findAllByLastName(lastname);
    }

    @Test
    void findByUsernameTest() {
        //arrange
        Mockito.when(userService.findByUsername(user.getUsername())).thenReturn(user);
        String username = "leandroasano";
        //act
        User found = userService.findByUsername(username);
        //assert
        Assertions.assertNotNull(found);
        Mockito.verify(userService).findByUsername(username);
    }

    @Test
    void findAllByDateTest() {
        //arrange
        Mockito.when(userService.findAllByDate(user.getDateofbirth())).thenReturn(users);
        String dateofbirth ="1996-04-26";
        //act
        List<User> foundedusers = userService.findAllByDate(LocalDate.parse(dateofbirth));
        //assert
        Assertions.assertNotNull(foundedusers);
        Mockito.verify(userService).findAllByDate(LocalDate.parse(dateofbirth));
    }

    @Test
    void findByEmailTest() {
        //arrange
        Mockito.when(userService.findByEmail(user.getEmail())).thenReturn(user);
        String email="leandroasano@gmail.com";
        //act
        User found = userService.findByEmail(email);
        //assert
        Assertions.assertNotNull(found);
        Mockito.verify(userService).findByEmail(email);
    }
}