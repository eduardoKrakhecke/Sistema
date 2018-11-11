package sis.module.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "riscos", schema = "glb")
public class Risco implements Serializable {


    private static final long serialVersionUID = -2049877076432653221L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_risco")
    private Long idRisco;

    @Column(name = "tipo_risco")
    private String tipoRisco;

    @Column(name = "taxa_juro")
    private Double taxaJuro;

    public Risco() {
    }

    public Long getIdRisco() {
        return idRisco;
    }

    public void setIdRisco(Long idRisco) {
        this.idRisco = idRisco;
    }

    public String getTipoRisco() {
        return tipoRisco;
    }

    public void setTipoRisco(String tipoRisco) {
        this.tipoRisco = tipoRisco;
    }

    public Double getTaxaJuro() {
        return taxaJuro;
    }

    public void setTaxaJuro(Double taxaJuro) {
        this.taxaJuro = taxaJuro;
    }
}
