package leandroasano.api.Controllers;

import leandroasano.api.Models.Product;
import leandroasano.api.Models.ProductCategory;
import leandroasano.api.Models.User;
import leandroasano.api.Services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductCategoryController {

    @Autowired
    ProductCategoryService productCategoryService;

    @PostMapping("/productcategory/add")
    public ResponseEntity addProductCategory(@RequestBody ProductCategory productCategory) throws Exception{
        try {
            productCategoryService.createProductCategory(productCategory);
            System.out.println("Category "+productCategory.getName()+"Created");
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e){
            throw new Exception("Error adding category");
        }
    }

    @PutMapping("/{idproductcategory}/update")
    public ResponseEntity updateProductCategory(@PathVariable("idproductcategory") int idprodcat,@RequestBody ProductCategory productCategory) throws Exception {
        try{
            productCategoryService.updateProductCategory(idprodcat, productCategory);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }
    }

    @GetMapping("/productcategory/read/{idprodcat}")
    public ResponseEntity<List<Product>> readProductCategory(@PathVariable("idprodcat") int idprodcat) throws Exception{
        try {
            return new ResponseEntity<>(productCategoryService.readProductCategory(idprodcat),HttpStatus.OK);
        } catch (Exception e){
            throw new Exception("Error of service");
        }
    }


    @GetMapping("/productcategory/all")
    public ResponseEntity<List<ProductCategory>> getAllProductsCategroy() throws Exception{
        try {
            return new ResponseEntity<>(productCategoryService.getAllProductCategories(),HttpStatus.OK);
        } catch (Exception e){
            throw new Exception("Error of service");
        }
    }

    @DeleteMapping("/{idprodcat}/delete")
    public ResponseEntity deleteUser(@PathVariable("idprodcat") int idprodcat) throws Exception {
        try{
            productCategoryService.deleteProductCategory(idprodcat);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }
    }
}
