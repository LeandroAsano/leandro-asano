package leandroasano.api.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iduser")
    @JsonIgnore
    private User userrol ; //should be called just "user"

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

    public User getUserrol() {
        return userrol;
    }

    public void setUserrol(User userrol) {
        this.userrol = userrol;
    }
}
