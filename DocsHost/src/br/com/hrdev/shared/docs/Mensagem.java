package br.com.hrdev.shared.docs;

import br.com.hrdev.shared.Shared;

/**
 *
 * @author henriqueschmidt
 */
public class Mensagem extends Shared {
    private static final long serialVersionUID = 1L;
    
    private String comando;
    private String descricao;
    private Shared value;

    public Mensagem(String comando, Shared value) {
        this.comando = comando;
        this.value = value;
        this.descricao = new String();
    }
    
    public Mensagem(boolean status, String descricao) {
        this.comando = status ? "sucesso" : "erro";
        this.value = null;
        this.descricao = descricao;
    }

    public Mensagem(String comando) {
        this(comando, null);
    }

    public String getComando() {
        return comando;
    }

    public Shared getValue() {
        return value;
    }
    
    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return this.comando;
    }
}
