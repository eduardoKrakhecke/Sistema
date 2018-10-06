package sis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "permissoes", schema = "glb")
public class Permissoes implements Serializable {

    private static final long serialVersionUID = -2840083173327078721L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPermissao;

    @Column(name = "inserir")
    private boolean inserir;

    @Column(name = "alterar")
    private boolean alterar;

    @Column(name = "excluir")
    private boolean excluir;

    @Column(name = "visualizar")
    private boolean visualizar;

    @JsonIgnore
    @JoinColumn(name="id_usuario")
    @ManyToOne()
    private Usuarios usuario;

    public Permissoes() {
    }

    public Long getIdPermissao() {
        return idPermissao;
    }

    public void setIdPermissao(Long idPermissao) {
        this.idPermissao = idPermissao;
    }

    public boolean isInserir() {
        return inserir;
    }

    public void setInserir(boolean inserir) {
        this.inserir = inserir;
    }

    public boolean isAlterar() {
        return alterar;
    }

    public void setAlterar(boolean alterar) {
        this.alterar = alterar;
    }

    public boolean isExcluir() {
        return excluir;
    }

    public void setExcluir(boolean excluir) {
        this.excluir = excluir;
    }

    public boolean isVisualizar() {
        return visualizar;
    }

    public void setVisualizar(boolean visualizar) {
        this.visualizar = visualizar;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}
