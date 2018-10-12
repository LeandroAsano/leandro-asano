package leandroasano.api.Services;

import leandroasano.api.Models.Product;
import leandroasano.api.Repositorys.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void createProduct(Product product){
        productRepository.save(product);
    }

    public void updateProduct(int idprod, Product productchanged){

        Product productunchanged = productRepository.getOne(idprod);

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

    public Product readProduct(int idprod){
        return productRepository.findById(idprod);
    }

    public void deleteProduct(int idprod){
        Product product = productRepository.findById(idprod);
        productRepository.delete(product);
    }

}
