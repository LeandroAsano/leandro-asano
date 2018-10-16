package leandroasano.api.Services;


import leandroasano.api.Models.Post;
import leandroasano.api.Models.Product;
import leandroasano.api.Models.ProductCategory;
import leandroasano.api.Models.User;
import leandroasano.api.Repositorys.PostRepository;
import leandroasano.api.Repositorys.ProductCategoryRepository;
import leandroasano.api.Repositorys.ProductRepository;
import leandroasano.api.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class PostService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    public void createPost(User user, Post post, Product product, ProductCategory productCategory){
        post.setDateofpost(LocalDate.now());
        post.setState("in Sale");
        post.setUserpost(user);
        post.setProduct(product);
        product.setPost(post);

        if (productCategory != null) {
            ProductCategory productCategoryfound = productCategoryRepository.findByname(productCategory.getName());
            if (Objects.nonNull(productCategoryfound)){
                post.getProduct().setCategory(productCategoryfound);
                productCategoryfound.getProducts().add(product);
            } else{
                post.getProduct().setCategory(null);
            }
        }  else {
            post.getProduct().setCategory(null);
        }

        postRepository.save(post);
        productRepository.save(product);
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

    public List<Post> showAllPostsByUser(String username){

        User userfound = userRepository.findByusername(username);

        return postRepository.findAllByuserpost(userfound);
    }


    public void deletePost(int idpost){
        productRepository.delete(postRepository.findByidpost(idpost).getProduct());
    }

    public List<Post> showAllPosts(){

        return postRepository.findAll();
    }
}
