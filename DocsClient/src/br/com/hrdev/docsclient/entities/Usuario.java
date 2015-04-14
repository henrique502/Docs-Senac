package br.com.hrdev.docsclient.entities;

/**
 *
 * @author henriqueschmidt
 */
public class Usuario extends Entity {
    
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
