package leandroasano.api.Repositorys;

import leandroasano.api.Models.Product;
import leandroasano.api.Models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository  extends JpaRepository<ProductCategory,Integer> {

    ProductCategory findByidprodcat(int idprodcat);

    ProductCategory findByname(String name);

}
