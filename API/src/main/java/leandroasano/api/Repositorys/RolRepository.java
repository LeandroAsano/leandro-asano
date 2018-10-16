package leandroasano.api.Repositorys;

import leandroasano.api.Models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol,Integer> {

    Rol findByrol(String rol);
}
