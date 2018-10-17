package leandroasano.api.Services;

import leandroasano.api.Models.Sale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;



@SpringBootTest
class SaleServiceTest {

    private static SaleService saleService;
    private static Sale sale;

    @BeforeAll
    public static void setUp(){
        saleService = Mockito.mock(SaleService.class);
        sale = Mockito.mock(Sale.class);
    }

    @Test
    void makeSaleTest() {
        //arrangee
        int idreserve = sale.getIdsale();
        //act
        saleService.makeSale(idreserve);
        //assert
        Mockito.verify(saleService).makeSale(idreserve);
    }

    @Test
    void showAllSalesOfPostsTest() {
        //arrange
        List<Sale> sales = new ArrayList<>();
        sales.add(sale);
        Mockito.when(saleService.showAllSalesOfPosts()).thenReturn(sales);
        //act
        List<Sale> foundsales = saleService.showAllSalesOfPosts();
        //assert
        Assertions.assertNotNull(foundsales);
        Mockito.verify(saleService).showAllSalesOfPosts();
    }

    @Test
    void deleleteReserve() {
        //arrange
       int idsale = sale.getIdsale();
        //act
        saleService.deleleteSale(idsale);
        //assert
        Mockito.verify(saleService).deleleteSale(idsale);
    }
}