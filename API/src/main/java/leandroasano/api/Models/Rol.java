package leandroasano.api.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Rols")
public class Rol {

    @Id
    @GeneratedValue
    @Column(name = "idrol")
    private int idrol;

    @Column(name = "Rol")
    private String rol;

    @ManyToMany(mappedBy = "rols")
    private List<User> userrols = new ArrayList<>();

    public Rol() {
    }

    public Rol(int idrol, String rol) {
        this.idrol = idrol;
        this.rol = rol;
    }

    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public List<User> getUserrols() {
        return userrols;
    }

    public void setUserrols(List<User> userrols) {
        this.userrols = userrols;
    }
}
