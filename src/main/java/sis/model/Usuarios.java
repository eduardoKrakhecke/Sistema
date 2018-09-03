package sis.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="usuarios" , schema="glb", uniqueConstraints= @UniqueConstraint(columnNames={"login"}))
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 8155620957857908117L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "login")
    private String login;

    @Column(name = "senha")
    private String senha;

    @Column(name = "flag_administrador")
    private int flagAdministrador;

    @Column(name = "foto_usuario", columnDefinition="TEXT")
    private String fotoUsuario;

    public Usuarios() {
    }

    public int getFlagAdministrador() {
        return flagAdministrador;
    }

    public void setFlagAdministrador(int flagAdministrador) {
        this.flagAdministrador = flagAdministrador;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(String fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }
}
