package leandroasano.api.Services;

import leandroasano.api.Models.Rol;
import leandroasano.api.Repositorys.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public void createRols(){
        Rol usr = new Rol();
        usr.setRol("user");

        Rol adm = new Rol();
        adm.setRol("admin");

        rolRepository.save(usr);
        rolRepository.save(adm);
    }

    public Rol obtainUserRol(){
        return rolRepository.findByrol("user");
    }

    public Rol obtainAdminRol(){
        return rolRepository.findByrol("admin");
    }
}
