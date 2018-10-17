package leandroasano.api.Services;

import leandroasano.api.Models.Rol;
import leandroasano.api.Models.Sale;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class RolServiceTest<Ro> {

    private static RolService rolService;
    private static Rol rol;

    @BeforeAll
    public static void setUp(){
        rolService = Mockito.mock(RolService.class);
        rol = Mockito.mock(Rol.class);
    }

    @Test
    void createRolsTest() {
        //arrange
        //act
        rolService.createRols();
        //assert
        Mockito.verify(rolService).createRols();
    }

    @Test
    void obtainUserRolTest() {
        //arrange
        Rol userrol = new Rol();
        userrol.setRol("user");
        Mockito.when(rolService.obtainUserRol()).thenReturn(userrol);
        //act
        String rol = rolService.obtainUserRol().getRol();
        //assert
        Assertions.assertNotNull(rol);
        Assertions.assertEquals(rol,"user");
    }

    @Test
    void obtainAdminRolTest() {
        //arrange
        Rol admrol = new Rol();
        admrol.setRol("admin");
        Mockito.when(rolService.obtainAdminRol()).thenReturn(admrol);
        //act
        String rol = rolService.obtainAdminRol().getRol();
        //assert
        Assertions.assertNotNull(rol);
        Assertions.assertEquals(rol,"admin");
    }
}