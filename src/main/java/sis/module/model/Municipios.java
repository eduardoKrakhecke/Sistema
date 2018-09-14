
package sis.module.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table( schema = "glb")
public class Municipios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_municipio", nullable = false)
    private Long idMunicipio;

    @Column(name = "id_uf", nullable = false)
    private long idUf;

    @Column(name = "codigo_dne", nullable = false, length = 8)
    private String codigoDne;

    @Column(nullable = false, length = 80)
    private String nome;

    @Column(length = 8)
    private String cep;

    @Column(name = "codigo_ibge", length = 7)
    private String codigoIbge;

    @Column(name = "flag_ativo")
    private Integer flagAtivo;

    @Column(name = "codigo_sefazrs", length = 3)
    private String codigoSefazrs;

    public Municipios() {
    }


    public Long getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Long idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public long getIdUf() {
        return idUf;
    }

    public void setIdUf(long idUf) {
        this.idUf = idUf;
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCodigoIbge() {
        return codigoIbge;
    }

    public void setCodigoIbge(String codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    public Integer getFlagAtivo() {
        return flagAtivo;
    }

    public void setFlagAtivo(Integer flagAtivo) {
        this.flagAtivo = flagAtivo;
    }

    public String getCodigoSefazrs() {
        return codigoSefazrs;
    }

    public void setCodigoSefazrs(String codigoSefazrs) {
        this.codigoSefazrs = codigoSefazrs;
    }


}
