package leandroasano.api.Repositorys;

import leandroasano.api.Models.Product;
import leandroasano.api.Models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

        Product findById(int id);

        List<Product> findAllBycategory(ProductCategory productCategory);

}
