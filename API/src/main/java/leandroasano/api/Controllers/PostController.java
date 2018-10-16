package leandroasano.api.Controllers;


import leandroasano.api.Models.Post;
import leandroasano.api.Models.Product;
import leandroasano.api.Models.ProductCategory;
import leandroasano.api.Models.User;
import leandroasano.api.Repositorys.PostRepository;
import leandroasano.api.Services.PostService;
import leandroasano.api.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    HttpSession session;

    @PostMapping("/post/add")
    public ResponseEntity addPost(@Valid @RequestBody Post post) throws Exception{
        try {
            User currentuser = userService.findUserbyuserid((int) session.getAttribute("iduser"));
            Product prod = post.getProduct();
            ProductCategory productCategory= prod.getCategory();
            postService.createPost(currentuser, post, prod,productCategory);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e){
            throw new Exception("Error of Posting");
        }
    }

    @PutMapping("/post/{idpost}/addstock/{stock}")
    public ResponseEntity updatePost(@PathVariable("idpost")int idpost, @PathVariable("stock") int stock) throws Exception {
        try{
            postService.addStockInPost(idpost, stock);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error of service");
        }
    }

    @GetMapping("/post/all")
    public ResponseEntity<List<Post>> showAllPosts() throws Exception {
        try {
            return new ResponseEntity<>(postService.showAllPosts(),HttpStatus.OK);
        } catch (Exception e){
            throw new Exception("Error of service");
        }
    }

    @GetMapping("/post/{idpost}")
    public ResponseEntity<Post> showPost(@PathVariable("idpost") int idpost) throws Exception {
        try {
            return new ResponseEntity<>(postService.readPost(idpost),HttpStatus.OK);
        } catch (Exception e){
            throw new Exception("Error of service");
        }
    }

    @GetMapping("/post/{username}")
    public ResponseEntity<List<Post>> showAllPostsByUsername(@PathVariable("username") String username) throws Exception {
        try {
            return new ResponseEntity<>(postService.showAllPostsByUser(username),HttpStatus.OK);
        } catch (Exception e){
            throw new Exception("Error of service");
        }
    }

    @DeleteMapping("/post/{idpost}")
    public ResponseEntity deletePost(@PathVariable("idpost") int idpost) throws Exception {
        try{
            postService.deletePost(idpost);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error of service");
        }
    }


}
