package leandroasano.api.Services;

import leandroasano.api.Models.Product;
import leandroasano.api.Models.ProductCategory;
import leandroasano.api.Repositorys.ProductCategoryRepository;
import leandroasano.api.Repositorys.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ProductCategoryService {

    @Autowired
    HttpSession session;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    ProductRepository productRepository;

    public void createProductCategory(ProductCategory productCategory) throws Exception {
        if (session.getAttribute("role")=="admin"){
            productCategoryRepository.save(productCategory);
        } else {
            throw new Exception("Error of permissions!");
        }

    }

    public void updateProductCategory(int idprodcat, ProductCategory productCategorychanged) throws Exception {
        if (session.getAttribute("role")=="admin"){
            ProductCategory productCategoryforchange = productCategoryRepository.findByidprodcat(idprodcat);

            if (!productCategorychanged.getName().isEmpty()){
                productCategoryforchange.setName(productCategorychanged.getName());
            }
            if (!productCategorychanged.getDescription().isEmpty()){
                productCategoryforchange.setDescription(productCategorychanged.getDescription());
            }
        } else {
            throw new Exception("Error of permissions!");
        }

    }

    public List<Product> readProductCategory(int idprodcat) throws Exception {
        if (session.getAttribute("role")=="admin"){
            ProductCategory productCategory = productCategoryRepository.findByidprodcat(idprodcat);
            return productCategory.getProducts();
        } else {
            throw new Exception("Error of permissions!");
        }
    }

    public List<ProductCategory> getAllProductCategories() throws Exception {
        if (session.getAttribute("role")=="admin"){
            return productCategoryRepository.findAll();
        } else {
            throw new Exception("Error of permissions!");
        }
    }

    public void deleteProductCategory(int idprodcat) throws Exception {
        if (session.getAttribute("role")=="admin"){
            ProductCategory productCategory = productCategoryRepository.findByidprodcat(idprodcat);
            productCategoryRepository.delete(productCategory);
        } else {
            throw new Exception("Error of permissions!");
        }

    }


}
