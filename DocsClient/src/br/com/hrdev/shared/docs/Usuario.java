package br.com.hrdev.shared.docs;

import br.com.hrdev.shared.Shared;
import java.util.ArrayList;

/**
 *
 * @author henriqueschmidt
 */
public class Usuario extends Shared {
    
    private static final long serialVersionUID = 1L;
    
    private String nome;
    private String senha;
    private ArrayList<Documento> documentos;
    private ArrayList<Documento> compartilhados;

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        this.documentos = new ArrayList<>();
        this.compartilhados = new ArrayList<>();
    }
    
    public Usuario(String nome) {
        this(nome, null);
    }
    
    public Usuario() {
        this(null, null);
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }
    
    
}
