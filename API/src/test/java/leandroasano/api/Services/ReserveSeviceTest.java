package leandroasano.api.Services;

import leandroasano.api.Models.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ReserveSeviceTest {

    private static ReserveSevice reserveSevice;
    private static Reserve reserve;

    @BeforeAll
    public static void setUp(){
        reserveSevice = Mockito.mock(ReserveSevice.class);
        reserve = Mockito.mock(Reserve.class);
    }

    @Test
    void makeReserveTest() {
        //arrange
        User currentuser = Mockito.mock(User.class);
        Post post = Mockito.mock(Post.class);
        LocalDate date = LocalDate.now();
        //act
        reserveSevice.makeReserve(post.getIdpost(),currentuser.getIduser(),date);
        //assert
        Mockito.verify(reserveSevice).makeReserve(post.getIdpost(),currentuser.getIduser(),date);
    }

    @Test
    void showReservesOfUserTest() {
        //arrange
        User user = Mockito.mock(User.class);
        List<Reserve> reserves = new ArrayList<>();
        reserves.add(reserve);

        Mockito.when(reserveSevice.showReservesOfUser(user.getUsername())).thenReturn(reserves);
        //act
        List<Reserve> foundReserves= reserveSevice.showReservesOfUser(user.getUsername());
        //assert
        Assertions.assertEquals(foundReserves,reserves);
    }

    @Test
    void showMyReservesTest() {
        //arrange
        User currentuser = Mockito.mock(User.class);
        List<Reserve> reserves = new ArrayList<>();
        reserves.add(reserve);
        Mockito.when(reserveSevice.showMyReserves(currentuser.getIduser())).thenReturn(reserves);
        //act
        List<Reserve> foundreserves = reserveSevice.showMyReserves(currentuser.getIduser());
        //assert
        Assertions.assertNotNull(foundreserves);
        Mockito.verify(reserveSevice).showMyReserves(currentuser.getIduser());
    }

    @Test
    void unamarkAllProductsReservationsTest() throws Exception {
        //arrange
        //act
        reserveSevice.unamarkAllProductsReservations();
        //assert
        Mockito.verify(reserveSevice).unamarkAllProductsReservations();
    }

    @Test
    void deleleteReserve() {
        //arrange
        int idreserve = reserve.getIdReserve();
        //act
        reserveSevice.deleleteReserve(idreserve);
        //assert
        Mockito.verify(reserveSevice).deleleteReserve(idreserve);
    }
}