package sis.module.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pessoas_contatos", schema = "glb")
public class PessoasContatos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa_contato")
    private Long idPessoaContato;

    @Column(name = "contato")
    private String contato;

    @JsonIgnore
    @JoinColumn(name="id_pessoa")
    @ManyToOne( )
    private Pessoas pessoa;

    public PessoasContatos() {
    }

    public Long getIdPessoaContato() {
        return idPessoaContato;
    }

    public void setIdPessoaContato(Long idPessoaContato) {
        this.idPessoaContato = idPessoaContato;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Pessoas getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoas pessoa) {
        this.pessoa = pessoa;
    }
}
