package leandroasano.api.Services;

import leandroasano.api.Models.Product;
import leandroasano.api.Models.ProductCategory;
import leandroasano.api.Repositorys.ProductCategoryRepository;
import leandroasano.api.Repositorys.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    ProductRepository productRepository;

    public void createProductCategory(ProductCategory productCategory){
        productCategoryRepository.save(productCategory);
    }

    public void updateProductCategory(int idprodcat, ProductCategory productCategorychanged){

        ProductCategory productCategoryforchange = productCategoryRepository.findById(idprodcat);

        if (!productCategorychanged.getName().isEmpty()){
            productCategoryforchange.setName(productCategorychanged.getName());
        }
        if (!productCategorychanged.getDescription().isEmpty()){
            productCategoryforchange.setDescription(productCategorychanged.getDescription());
        }
    }

    public List<Product> readProductCategory(int idprodcat){

        ProductCategory productCategory = productCategoryRepository.findById(idprodcat);
        return productRepository.findAllBycategory(productCategory);
    }

    public void deleteProductCategory(int idprodcat){
        ProductCategory productCategory = productCategoryRepository.findById(idprodcat);
        productCategoryRepository.delete(productCategory);
    }


}
