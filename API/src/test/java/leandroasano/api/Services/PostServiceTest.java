package leandroasano.api.Services;

import leandroasano.api.Models.Post;
import leandroasano.api.Models.Product;
import leandroasano.api.Models.ProductCategory;
import leandroasano.api.Models.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    private static PostService postService;
    private static Post post;

    @BeforeAll
    public static void setUp(){
        postService= Mockito.mock(PostService.class);
        post= Mockito.mock(Post.class);
    }

    @Test
    void createPostTest() {
        //arrange
        User user = Mockito.mock(User.class);
        Product product = Mockito.mock(Product.class);
        ProductCategory productCategory = Mockito.mock(ProductCategory.class);
        //act
        postService.createPost(user,post,product,productCategory);
        //assert
        Mockito.verify(postService).createPost(user,post,product,productCategory);
    }

    @Test
    void addStockInPostTest() {
        //arrange
        Product product = Mockito.mock(Product.class);
        int idprod = product.getidprod();
        int stocktoadd = 5;
        //act
        postService.addStockInPost(idprod,stocktoadd);
        //assert
        Mockito.verify(postService).addStockInPost(idprod,stocktoadd);
    }

    @Test
    void readPostTest() {
        //arrange
        Mockito.when(postService.readPost(post.getIdpost())).thenReturn(post);
        //act
        Post foundpost = postService.readPost(post.getIdpost());
        //assert
        Assertions.assertNotNull(foundpost);
        Assertions.assertEquals(foundpost,post);
        Mockito.verify(postService).readPost(post.getIdpost());
    }

    @Test
    void showAllPostsByUserTest() {
        //arrange
        User user = new User();
        user.setUsername("leandroasano");
        Post post1 = new Post();
        post1.setUserpost(user);
        List<Post> posts = new ArrayList<>();
        posts.add(post1);
        Mockito.when(postService.showAllPostsByUser(post1.getUserpost().getUsername())).thenReturn(posts);
        String username = "leandroasano";
        //act
        List<Post> foundposts= postService.showAllPostsByUser(username);
        //assert
        Assertions.assertNotNull(foundposts);
        Assertions.assertEquals(foundposts,posts);
        Mockito.verify(postService).showAllPostsByUser(username);
    }

    @Test
    void deletePostTest() {
        //arrange
        int idpost = post.getIdpost();
        //act
        postService.deletePost(idpost);
        //assert
        Mockito.verify(postService).deletePost(idpost);
    }

    @Test
    void showAllPostsTest() {
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        Mockito.when(postService.showAllPosts()).thenReturn(posts);
        //act
        List<Post> foundposts= postService.showAllPosts();
        //assert
        Assertions.assertNotNull(foundposts);
        Mockito.verify(postService).showAllPosts();
    }
}