package leandroasano.api.Services;


import leandroasano.api.Models.Post;
import leandroasano.api.Models.Product;
import leandroasano.api.Models.User;
import leandroasano.api.Repositorys.PostRepository;
import leandroasano.api.Repositorys.ProductRepository;
import leandroasano.api.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    public void createPost(User user,Post post, Product product){
        post.setUserpost(user);
        post.setProduct(product);
        productRepository.save(product);
        postRepository.save(post);
    }

    public void addStockInPost(int idprod, int stock){

        Post postunchanged = postRepository.getOne(idprod);

        if (stock != 0){
            postunchanged.setStock(postunchanged.getStock()+stock);
        }
        postRepository.save(postunchanged);
    }

    public Post readPost(int idpost){
        return postRepository.findByidpost(idpost);
    }

    public void soldAProduct(int idpost){
        Post postformark = postRepository.findByidpost(idpost);
        postformark.setStock(postformark.getStock()-1);
        if (postformark.getStock()==0){
            postformark.setState("Sold");
        }else {
            System.out.println("Still have "+postformark.getStock()+" in stock");
        }
    }

    public List<Post> showAllPostsByUser(String username){

        User userfound = userRepository.findByusername(username);

        return postRepository.findAllByuserpost(userfound);
    }

    public void deletePost(Post post){
        productRepository.delete(post.getProduct());
        postRepository.delete(post);
    }


    public void deletePost(int idpost){
        productRepository.delete(postRepository.findByidpost(idpost).getProduct());
        postRepository.delete(postRepository.findByidpost(idpost));
    }

    public List<Post> showAllPosts(){

        return postRepository.findAll();
    }
}
