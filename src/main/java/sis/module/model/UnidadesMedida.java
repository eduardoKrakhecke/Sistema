package sis.module.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "unidades_medida", schema = "glb")
public class UnidadesMedida implements Serializable {

    private static final long serialVersionUID=421233366058833945L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unidade")
    private Long idUnidade;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "sigla")
    private String sigla;

    public UnidadesMedida() {
    }

    public Long getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Long idUnidade) {
        this.idUnidade = idUnidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
