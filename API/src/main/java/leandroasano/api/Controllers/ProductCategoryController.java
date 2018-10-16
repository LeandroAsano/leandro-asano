package leandroasano.api.Controllers;

import leandroasano.api.Models.Product;
import leandroasano.api.Models.ProductCategory;
import leandroasano.api.Models.User;
import leandroasano.api.Services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class ProductCategoryController {

    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    HttpSession session;

    @PostMapping("/productcategory/add")
    public ResponseEntity addProductCategory(@RequestBody ProductCategory productCategory) throws Exception{
       try {
           if (session.getAttribute("role").equals("admin")) {
               productCategoryService.createProductCategory(productCategory);
               System.out.println("Category " + productCategory.getName() + "Created");
               return new ResponseEntity(HttpStatus.CREATED);
           } else {
               throw new Exception("Error of permissions!");
           }
       } catch (Exception e){
           throw e;
       }
    }

    @PutMapping("/productcategory/{idproductcategory}")
    public ResponseEntity updateProductCategory(@PathVariable("idproductcategory") int idproductcategory,@RequestBody ProductCategory productCategory) throws Exception {
        if (session.getAttribute("role").equals("admin")){
            productCategoryService.updateProductCategory(idproductcategory, productCategory);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            throw new Exception("Error of permissions!");
        }
    }

    @GetMapping("/productcategory/read/{idprodcat}")
    public ResponseEntity<List<Product>> readProductCategory(@PathVariable("idprodcat") int idprodcat) throws Exception{
        if (session.getAttribute("role").equals("admin")){
            return new ResponseEntity<>(productCategoryService.readProductCategory(idprodcat),HttpStatus.OK);
        } else {
            throw new Exception("Error of permissions!");
        }
    }


    @GetMapping("/productcategory/all")
    public ResponseEntity<List<ProductCategory>> getAllProductsCategroy() throws Exception{
        if (session.getAttribute("role").equals("admin")){
            return new ResponseEntity<>(productCategoryService.getAllProductCategories(),HttpStatus.OK);
        } else {
            throw new Exception("Error of permissions!");
        }
    }

    @DeleteMapping("/productcategory/{idprodcat}")
    public ResponseEntity deleteUser(@PathVariable("idprodcat") int idprodcat) throws Exception {
        if (session.getAttribute("role").equals("admin")){
            productCategoryService.deleteProductCategory(idprodcat);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            throw new Exception("Error of permissions!");
        }
    }
}
