package leandroasano.api.Services;

import leandroasano.api.Models.Post;
import leandroasano.api.Models.Product;
import leandroasano.api.Models.Sale;
import leandroasano.api.Models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;


@SpringBootTest
class ProductServiceTest {

    private static ProductService productService;
    private static Product product;

    @BeforeAll
    public static void setUp(){
        productService= Mockito.mock(ProductService.class);
        product= Mockito.mock(Product.class);
    }

    @Test
    void updateProductTest() {
        //arrange
        Product product2 = new Product();
        product2.setDescription("newDescription");
        //act
        productService.updateProduct(product.getidprod(),product2);
        //assert
        Mockito.verify(productService).updateProduct(product.getidprod(),product2);
    }

    @Test
    void readProductTest() {
        //arrange
        Post post = Mockito.mock(Post.class);
        Mockito.when(productService.readProduct(post.getIdpost())).thenReturn(product);
        //act
        Product foundproduct = productService.readProduct(post.getIdpost());
        //assert
        Assertions.assertNotNull(foundproduct);
        Mockito.verify(productService).readProduct(post.getIdpost());
    }

    @Test
    void getAllProductsTest() {
        List<Product> products = new ArrayList<>();
        products.add(product);
        Mockito.when(productService.getAllProducts()).thenReturn(products);
        //act
        List<Product> foundproducts = productService.getAllProducts();
        //assert
        Assertions.assertNotNull(foundproducts);
        Mockito.verify(productService).getAllProducts();
    }

    @Test
    void getDetailProductTest() {
        //arrange
        Product prod = new Product();
        prod.setName("cheese");
        prod.setDescription("description");

        Mockito.when(productService.getDetailProduct(prod.getName())).thenReturn(prod.getDescription());
        //act
        String detail = productService.getDetailProduct("cheese");
        //assert
        Assertions.assertNotNull(detail);
        Assertions.assertEquals(prod.getDescription(),detail);
        Mockito.verify(productService).getDetailProduct(prod.getName());
    }
}