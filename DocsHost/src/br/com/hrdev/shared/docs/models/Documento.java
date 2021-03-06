package br.com.hrdev.shared.docs.models;

import br.com.hrdev.shared.docs.Shared;

/**
 *
 * @author henriqueschmidt
 */
public class Documento extends Shared {
    
    private static final long serialVersionUID = 1L;
    
    private String titulo;
    private String conteudo;
    private Long autor;

    public Documento() {
        this.autor = null;
        this.titulo = "Sem título";
        this.conteudo = "";
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Long getAutor() {
        return autor;
    }

    public void setAutor(Long autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return this.titulo;
    }
}
