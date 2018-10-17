package leandroasano.api.Services;

import leandroasano.api.Models.Post;
import leandroasano.api.Models.Product;
import leandroasano.api.Models.ProductCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ProductCategoryServiceTest {

    private static ProductCategoryService productCategoryService;
    private static ProductCategory productCategory;

    @BeforeAll
    public static void setUp(){
        productCategoryService= Mockito.mock(ProductCategoryService.class);
        productCategory= Mockito.mock(ProductCategory.class);
    }

    @Test
    void createProductCategoryTest() throws Exception {
        //arrange
        //act
        productCategoryService.createProductCategory(productCategory);
        //assert
        Mockito.verify(productCategoryService).createProductCategory(productCategory);
    }

    @Test
    void updateProductCategory() throws Exception {
        //arrange
        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setName("food");
        productCategory2.setName("all freezeable foods");
        //act
        productCategoryService.updateProductCategory(productCategory.getIdprodcat(),productCategory2);
        //assert
        Mockito.verify(productCategoryService).updateProductCategory(productCategory.getIdprodcat(),productCategory2);
    }

    @Test
    void readProductCategory() throws Exception {
        //arrange
        Product product = Mockito.mock(Product.class);
        List<Product> products = new ArrayList<>();
        products.add(product);
        Mockito.when(productCategoryService.readProductCategory(productCategory.getIdprodcat())).thenReturn(products);
        //act
        List<Product> foundproducts= productCategoryService.readProductCategory(productCategory.getIdprodcat());
        //assert
        Assertions.assertNotNull(foundproducts);
        Assertions.assertEquals(foundproducts,products);
        Mockito.verify(productCategoryService).readProductCategory(productCategory.getIdprodcat());
    }

    @Test
    void getAllProductCategories() throws Exception {
        //arrange
        List<ProductCategory> productcategories = new ArrayList<>();
        productcategories.add(productCategory);
        Mockito.when(productCategoryService.getAllProductCategories()).thenReturn(productcategories);
        //act
        List<ProductCategory> foundproductscategories= productCategoryService.getAllProductCategories();
        //assert
        Assertions.assertNotNull(foundproductscategories);
        Mockito.verify(productCategoryService).getAllProductCategories();
    }

    @Test
    void deleteProductCategory() throws Exception {
        //arrange
        int idproductcategory = productCategory.getIdprodcat();
        //act
        productCategoryService.deleteProductCategory(idproductcategory);
        //assert
        Mockito.verify(productCategoryService).deleteProductCategory(idproductcategory);
    }
}