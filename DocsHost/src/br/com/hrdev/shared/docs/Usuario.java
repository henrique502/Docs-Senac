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

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        this.documentos = new ArrayList<>();
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ArrayList<Documento> getDocumentos() {
        return documentos;
    }
}
