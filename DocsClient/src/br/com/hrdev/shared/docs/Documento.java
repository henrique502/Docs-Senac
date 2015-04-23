package br.com.hrdev.shared.docs;

import br.com.hrdev.shared.Shared;

/**
 *
 * @author henriqueschmidt
 */
public class Documento extends Shared {
    
    private static final long serialVersionUID = 1L;
    
    private String titulo;
    private String conteudo;
    private final Usuario autor;

    public Documento(Usuario autor) {
        this.autor = autor;
        this.titulo = "Sem título";
        this.conteudo = "";
    }

    public Documento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public Usuario getAutor() {
        return autor;
    }

    @Override
    public String toString() {
        return this.titulo;
    }
}
