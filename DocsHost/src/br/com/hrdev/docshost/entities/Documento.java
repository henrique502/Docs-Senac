package br.com.hrdev.docshost.entities;

import java.io.Serializable;
import javax.swing.Icon;

/**
 *
 * @author henriqueschmidt
 */
public class Documento extends Entity implements Serializable {
    
    private String titulo;
    private Usuario autor;

    public Documento() {
        this(null, null);
    }

    public Documento(String titulo, Usuario autor) {
        this.titulo = titulo;
        this.autor = autor;
    }
    

    @Override
    public String getNome() {
        return titulo;
    }

    public Icon getIcon() {
        return null;
    }

    public Usuario getAutor() {
        return autor;
    }

}
