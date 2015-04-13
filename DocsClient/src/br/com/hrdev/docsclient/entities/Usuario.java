package br.com.hrdev.docsclient.entities;

import java.io.Serializable;

/**
 *
 * @author henriqueschmidt
 */
public class Usuario implements Entity, Serializable {
    
    private String nome;

    public Usuario(String nome) {
        this.nome = nome;
    }

    public Usuario() {
        this(null);
    }
    
    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public String getNome() {
        return nome;
    }
    
}
