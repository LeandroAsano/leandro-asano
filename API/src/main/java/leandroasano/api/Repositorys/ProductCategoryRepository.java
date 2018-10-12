package leandroasano.api.Repositorys;

import leandroasano.api.Models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository  extends JpaRepository<ProductCategory,Integer> {



    ProductCategory findById(int idprodcat);
}
