package leandroasano.api.Controllers;


import leandroasano.api.Models.Product;
import leandroasano.api.Models.User;
import leandroasano.api.Repositorys.ProductRepository;
import leandroasano.api.Services.PostService;
import leandroasano.api.Services.ProductService;
import leandroasano.api.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    //The addition of products is responsability of the PostController(when add a post)

    @PutMapping("/{idpost}/product/update")
    public ResponseEntity updateProduct(@PathVariable("idpost") int idpost ,@RequestBody Product product) throws Exception {
        try {
            productService.updateProduct(idpost,product);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("Error in service");
        }
    }

    @GetMapping("/users/{idpost}/product")
    public ResponseEntity<Product> getProduct(@PathVariable("idpost") int idpost) throws Exception {
        try{
            return new ResponseEntity<>(productService.readProduct(idpost),HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }
    }

    @GetMapping("/users/product/detail/{name}")
    public ResponseEntity<String> getProductDetailbyname(@PathVariable("name") String name) throws Exception {
        try{
            return new ResponseEntity<>(productService.getDetailProduct(name),HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }
    }

    @GetMapping("/users/product/all")
    public ResponseEntity<List<Product>> getAllProducts() throws Exception {
        try{
            return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }
    }

    //The delete of products is responsability of the PostController(when a post is deleted)
}
