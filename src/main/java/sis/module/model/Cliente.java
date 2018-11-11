package sis.module.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "clientes", schema = "glb")
public class Cliente implements Serializable {


    private static final long serialVersionUID = -6013621140192753148L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Column(name = "limite_credito")
    private Double limiteCredito;

    @NotNull
    @JoinColumn(name = "id_risco")
    @ManyToOne()
    private Risco risco;


    public Cliente() {
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(Double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public Risco getRisco() {
        return risco;
    }

    public void setRisco(Risco risco) {
        this.risco = risco;
    }
}
