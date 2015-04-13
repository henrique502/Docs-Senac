package br.com.hrdev.docsclient.entities;

import br.com.hrdev.docsclient.Main;
import java.io.Serializable;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author henriqueschmidt
 */
public class Documento implements Entity, Serializable {
    
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
    public Integer getId() {
        return null;
    }

    @Override
    public String getNome() {
        return titulo;
    }

    public Icon getIcon() {
        return new ImageIcon(Main.getInstance().getAssets("doc.png"));
    }

    public Usuario getAutor() {
        return autor;
    }

}
