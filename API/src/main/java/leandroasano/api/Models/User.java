package leandroasano.api.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private int iduser;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "username")
    private String username;

    @Column(name = "dateofbirth")
    private LocalDate dateofbirth;

    @Column(name = "email")
    private String email;

    @Column(name = "pass")
    private String pass;
    @OneToMany(mappedBy = "userpost",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Post> userposts = new ArrayList<>();

    @OneToMany(mappedBy = "userres",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reserve> reserves = new ArrayList<>();

    @ManyToMany(mappedBy = "userrol",cascade = CascadeType.ALL)
    private List<Rol> rols = new ArrayList<>();

    public User() {
    }

    public User(int iduser, String firstname, String lastname, String username, LocalDate dateofbirth, String email, String pass, List<Post> userposts, List<Reserve> reserves, List<Rol> rols) {
        this.iduser = iduser;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.dateofbirth = dateofbirth;
        this.email = email;
        this.pass = pass;
        this.userposts = userposts;
        this.reserves = reserves;
        this.rols = rols;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(LocalDate dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public List<Post> getUserposts() {
        return userposts;
    }

    public void setUserposts(List<Post> userposts) {
        this.userposts = userposts;
    }

    public List<Reserve> getReserves() {
        return reserves;
    }

    public void setReserves(List<Reserve> reserves) {
        this.reserves = reserves;
    }

    public List<Rol> getRols() {
        return rols;
    }

    public void setRols(List<Rol> rols) {
        this.rols = rols;
    }
}