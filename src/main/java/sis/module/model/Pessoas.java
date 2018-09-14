package sis.module.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "pessoas", schema = "glb")
public class Pessoas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
    private Long idPessoa;

    @Column(name = "foto", columnDefinition = "TEXT")
    private String foto;

    @Column(name = "nome")
    private String nome;

    @Column(name = "rg")
    private String rg;

    @Column(name = "cpf")
    private String cpf;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="pessoa")
    private List<PessoasContatos> pessoaContato;

    @JoinColumn(name = "id_municipio")
    @ManyToOne()
    private Municipios municipio;

    @JoinColumn(name = "id_pais")
    @ManyToOne()
    private Paises pais;

    @JoinColumn(name = "id_uf")
    @ManyToOne()
    private Ufs uf;

    public Pessoas() {
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<PessoasContatos> getPessoaContato() {
        return pessoaContato;
    }

    public void setPessoaContato(List<PessoasContatos> pessoaContato) {
        this.pessoaContato = pessoaContato;
    }

    public Municipios getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipios municipio) {
        this.municipio = municipio;
    }

    public Paises getPais() {
        return pais;
    }

    public void setPais(Paises pais) {
        this.pais = pais;
    }

    public Ufs getUf() {
        return uf;
    }

    public void setUf(Ufs uf) {
        this.uf = uf;
    }
}
