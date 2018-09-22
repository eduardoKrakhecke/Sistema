package sis.module.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pessoas_documentos", schema = "glb")
public class PessoasDocumentos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa_documento")
    private Long idPessoaDocumento;

    @Column(name = "tipo_documento")
    private String tipoDocumento;

    @Column(name = "documento")
    private String documento;

    @JsonIgnore
    @JoinColumn(name="id_pessoa")
    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY , optional = false)
    private Pessoas pessoa;

    public PessoasDocumentos() {
    }

    public Long getIdPessoaDocumento() {
        return idPessoaDocumento;
    }

    public void setIdPessoaDocumento(Long idPessoaDocumento) {
        this.idPessoaDocumento = idPessoaDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Pessoas getPessoa() {
        return pessoa;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setPessoa(Pessoas pessoa) {
        this.pessoa = pessoa;
    }
}
