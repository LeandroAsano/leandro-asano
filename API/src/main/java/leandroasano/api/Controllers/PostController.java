package leandroasano.api.Controllers;


import leandroasano.api.Models.Post;
import leandroasano.api.Models.Product;
import leandroasano.api.Models.User;
import leandroasano.api.Repositorys.PostRepository;
import leandroasano.api.Services.PostService;
import leandroasano.api.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    HttpSession session;

    @PostMapping("/users/posts/add")
    public ResponseEntity addPost(@RequestBody Post post, @RequestBody Product product) throws Exception{
        try {
            User currentuser = userService.findUserbyuserid((int) session.getAttribute("iduser"));
            postService.createPost(currentuser, post,product);
            System.out.println("Post Created");
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e){
            throw new Exception("Error adding Post");
        }
    }

    @PutMapping("/users/{idpost}/addstock/")
    public ResponseEntity updatePost(@PathVariable("idpost")int idpost, @RequestBody int stock) throws Exception {
        try{
            postService.addStockInPost(idpost, stock);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }
    }

    @PutMapping("/users/posts/update/sold")
    public ResponseEntity markAsSold(@PathVariable("idpost")int idpost) throws Exception {
        try{
            postService.soldAProduct(idpost);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }
    }

    @GetMapping("/users/posts/all")
    public ResponseEntity<List<Post>> showAllPosts() throws Exception {
        try {
            return new ResponseEntity<>(postService.showAllPosts(),HttpStatus.OK);
        } catch (Exception e){
            throw new Exception("Error getting Posts");
        }
    }

    @GetMapping("/users/posts/{idpost}")
    public ResponseEntity<Post> showPost(@PathVariable("idpost") int idpost) throws Exception {
        try {
            return new ResponseEntity<>(postService.readPost(idpost),HttpStatus.OK);
        } catch (Exception e){
            throw new Exception("Error getting Posts");
        }
    }

    @GetMapping("/{username}/posts/all")
    public ResponseEntity<List<Post>> showAllPostsByUsername(@PathVariable("username") String username) throws Exception {
        try {
            return new ResponseEntity<>(postService.showAllPostsByUser(username),HttpStatus.OK);
        } catch (Exception e){
            throw new Exception("Error getting Posts");
        }
    }

    @DeleteMapping("/users/posts/delete/{idpost}")
    public ResponseEntity deletePost(@PathVariable("idpost") int idpost) throws Exception {
        try{
            postService.deletePost(idpost);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }
    }

    @DeleteMapping("/users/posts/delete")
    public ResponseEntity deletePost(@RequestBody Post post) throws Exception {
        try{
            postService.deletePost(post);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Error in service");
        }
    }




}
