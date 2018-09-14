
package sis.module.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table( schema = "glb")
public class Paises implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pais")
    private Long idPais;

    @Basic(optional = false)
    @Column(name = "sigla_2", nullable = false, length = 2)
    private String sigla2;

    @Basic(optional = false)
    @Column(nullable = false, length = 72)
    private String nome;

    @Column(name = "nome_ingles", length = 72)
    private String nomeIngles;

    @Column(name = "flag_ativo")
    private Integer flagAtivo;

    @Column(name = "codigo_sefazrs", length = 3)
    private String codigoSefazrs;

    public Paises() {
    }

    public Long getIdPais() {
        return idPais;
    }

    public void setIdPais(Long idPais) {
        this.idPais = idPais;
    }

    public String getSigla2() {
        return sigla2;
    }

    public void setSigla2(String sigla2) {
        this.sigla2 = sigla2;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeIngles() {
        return nomeIngles;
    }

    public void setNomeIngles(String nomeIngles) {
        this.nomeIngles = nomeIngles;
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
