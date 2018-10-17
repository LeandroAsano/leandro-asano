package leandroasano.api.Services;

import leandroasano.api.Models.Product;
import leandroasano.api.Models.ProductCategory;
import leandroasano.api.Repositorys.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductCategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;


    public void createProductCategory(ProductCategory productCategory) throws Exception {
        if (Objects.nonNull(productCategoryRepository.findByname(productCategory.getName()))){
            throw new Exception("Category Already Exists!");
        } else {
            productCategoryRepository.save(productCategory);
        }
    }

    public void updateProductCategory(int idprodcat, ProductCategory productCategorychanged) throws Exception {
        ProductCategory productCategoryforchange = productCategoryRepository.findByidprodcat(idprodcat);
        if (Objects.nonNull(productCategorychanged.getName())){
            productCategoryforchange.setName(productCategorychanged.getName());
        }
        if (!productCategorychanged.getDescription().isEmpty()){
            productCategoryforchange.setDescription(productCategorychanged.getDescription());
        }
        productCategoryRepository.save(productCategoryforchange);
    }

    public List<Product> readProductCategory(int idprodcat) throws Exception {
        ProductCategory productCategory = productCategoryRepository.findByidprodcat(idprodcat);
        return productCategory.getProducts();
    }

    public List<ProductCategory> getAllProductCategories() throws Exception {
        return productCategoryRepository.findAll();
    }

    public void deleteProductCategory(int idprodcat) throws Exception {
        ProductCategory productCategory = productCategoryRepository.findByidprodcat(idprodcat);
        productCategoryRepository.delete(productCategory);
    }


}
