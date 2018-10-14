package leandroasano.api.Services;

import leandroasano.api.Models.Post;
import leandroasano.api.Models.Product;
import leandroasano.api.Models.ProductCategory;
import leandroasano.api.Models.Reserve;
import leandroasano.api.Repositorys.PostRepository;
import leandroasano.api.Repositorys.ProductRepository;
import leandroasano.api.Repositorys.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PostRepository postRepository;

    //The creation of products is responsability of PostController

    public void updateProduct(int idpost, Product productchanged){
        Post post = postRepository.findByidpost(idpost);
        Product productunchanged = productRepository.findBypost(post);

        if (!productchanged.getName().isEmpty()){
            productunchanged.setName(productchanged.getName());
        }
        if (!productchanged.getDescription().isEmpty()){
            productunchanged.setDescription(productchanged.getDescription());
        }
        if (!productchanged.getImageurl().isEmpty()){
            productunchanged.setImageurl(productchanged.getImageurl());
        }
        if (productchanged.getPrice()!=productunchanged.getPrice()){
            productunchanged.setPrice(productchanged.getPrice());
        }
        if (productchanged.getUsed()!=productunchanged.getUsed()){
            productunchanged.setUsed(productchanged.getUsed());
        }

        productRepository.save(productunchanged);
    }

    public Product readProduct(int idpost){
        Post post = postRepository.findByidpost(idpost);
        return post.getProduct();
    }

    //delete products its responsability of PostController(when a post is deleted)

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public String getDetailProduct(String name){
        Product productFound = productRepository.findByname(name);
        return productFound.getDescription();
    }
}
