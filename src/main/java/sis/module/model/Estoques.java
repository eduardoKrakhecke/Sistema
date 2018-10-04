package sis.module.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "estoques", schema = "glb")
public class Estoques implements Serializable {

    private static final long serialVersionUID = -1878236401928281778L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estoque")
    private Long idEstoque;

    @Column(name = "quantidade")
    private Long quantidade;

    @Column(name = "valor")
    private Long valor;

    @JoinColumn(name = "id_produto")
    @ManyToOne()
    private Produtos produto;

    @JoinColumn(name = "id_unidade")
    @ManyToOne()
    private UnidadesMedida unidadeMedida;


    public Estoques() {
    }

    public Long getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(Long idEstoque) {
        this.idEstoque = idEstoque;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public UnidadesMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadesMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }
}
