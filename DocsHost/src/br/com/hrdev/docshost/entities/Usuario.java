package br.com.hrdev.docshost.entities;

import java.io.Serializable;

/**
 *
 * @author henriqueschmidt
 */
public class Usuario extends Entity implements Serializable {
    
    private static final long serialVersionUID = 2L;
    private String nome;
    private String senha;

    public Usuario(String nome) {
        this.nome = nome;
    }

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }
    
    public Usuario() {
        this(null);
    }

    @Override
    public String getNome() {
        return nome;
    }
    
}
