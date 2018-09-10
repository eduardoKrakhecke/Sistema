package sis.model;

import java.util.List;

public class Pagina {

    private int numeroDaPagina;
    private Long quantidadeDeRegistros;
    private Long quantidadeDePaginas;
    private int tamanhoDaPagina;
    private Boolean ultimaPagina;
    private Boolean primeiraPagina;
    private List<Object> Conteudo;

    public Pagina() {

    }

    public int getNumeroDaPagina() {
        return numeroDaPagina;
    }

    public void setNumeroDaPagina(int numeroDaPagina) {
        this.numeroDaPagina = numeroDaPagina;
    }

    public Long getQuantidadeDeRegistros() {
        return quantidadeDeRegistros;
    }

    public void setQuantidadeDeRegistros(Long quantidadeDeRegistros) {
        this.quantidadeDeRegistros = quantidadeDeRegistros;
    }

    public Long getQuantidadeDePaginas() {
        return quantidadeDePaginas;
    }

    public void setQuantidadeDePaginas(Long quantidadeDePaginas) {
        this.quantidadeDePaginas = quantidadeDePaginas;
    }

    public int getTamanhoDaPagina() {
        return tamanhoDaPagina;
    }

    public void setTamanhoDaPagina(int tamanhoDaPagina) {
        this.tamanhoDaPagina = tamanhoDaPagina;
    }

    public Boolean getUltimaPagina() {
        return ultimaPagina;
    }

    public void setUltimaPagina(Boolean ultimaPagina) {
        this.ultimaPagina = ultimaPagina;
    }

    public Boolean getPrimeiraPagina() {
        return primeiraPagina;
    }

    public void setPrimeiraPagina(Boolean primeiraPagina) {
        this.primeiraPagina = primeiraPagina;
    }

    public List<Object> getConteudo() {
        return Conteudo;
    }

    public void setConteudo(List<Object> conteudo) {
        Conteudo = conteudo;
    }

}
