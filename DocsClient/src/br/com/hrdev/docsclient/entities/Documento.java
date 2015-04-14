package br.com.hrdev.docsclient.entities;

import br.com.hrdev.docsclient.Main;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author henriqueschmidt
 */
public class Documento extends Entity {
    
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
        return new ImageIcon(Main.getInstance().getAssets("doc.png"));
    }

    public Usuario getAutor() {
        return autor;
    }

}
