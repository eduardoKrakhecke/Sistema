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

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "celular")
    private String celular;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="pessoa")
    private List<PessoasDocumentos> pessoaDocumentos;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public List<PessoasDocumentos> getPessoaDocumentos() {
        return pessoaDocumentos;
    }

    public void setPessoaDocumentos(List<PessoasDocumentos> pessoaDocumentos) {
        this.pessoaDocumentos = pessoaDocumentos;
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
