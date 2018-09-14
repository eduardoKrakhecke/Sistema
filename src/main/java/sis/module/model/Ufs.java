
package sis.module.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(schema = "glb")
public class Ufs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_uf", nullable = false)
    private Long idUf;

    @Column(name = "id_pais", nullable = false)
    private long idPais;

    @Column(nullable = false, length = 2)
    private String sigla;

    @Column(name = "codigo_dne", nullable = false, length = 2)
    private String codigoDne;

    @Column(nullable = false, length = 72)
    private String nome;

    @Column(name = "codigo_sefazrs", length = 2)
    private String codigoSefazrs;

    @Column(name = "nome_exibicao", length = 255)
    private String nomeExibicao;

    public Ufs() {
    }


    public Long getIdUf() {
        return idUf;
    }

    public void setIdUf(Long idUf) {
        this.idUf = idUf;
    }

    public long getIdPais() {
        return idPais;
    }

    public void setIdPais(long idPais) {
        this.idPais = idPais;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getCodigoDne() {
        return codigoDne;
    }

    public void setCodigoDne(String codigoDne) {
        this.codigoDne = codigoDne;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoSefazrs() {
        return codigoSefazrs;
    }

    public void setCodigoSefazrs(String codigoSefazrs) {
        this.codigoSefazrs = codigoSefazrs;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public void setNomeExibicao(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

}
