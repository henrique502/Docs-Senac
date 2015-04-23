package br.com.hrdev.docshost;

import br.com.hrdev.shared.docs.Documento;
import br.com.hrdev.shared.docs.Usuario;
import java.util.ArrayList;

/**
 *
 * @author henriqueschmidt
 */
public class Storage {
    
    private static Storage instance = null;
    
    private final ArrayList<Usuario> usuarios;
    private final ArrayList<Documento> documentos;
    
    protected Storage(){
        usuarios = new ArrayList<>();
        documentos = new ArrayList<>();
    }
    
    public static Storage getInstance(){
        if(instance == null){
            instance = new Storage();
        }
        return instance;
    }

    public Usuario getUsuarioByLogin(Usuario usuario) {
        
        for(Usuario u : usuarios){
            if(u.getNome().equals(usuario.getNome()) && u.getSenha().equals(usuario.getSenha())){
                return u;
            }
        }
        
        return null;
    }

    public void saveUsuario(Usuario usuario) {
        Usuario u = new Usuario();
        u.setNome(usuario.getNome());
        u.setSenha(usuario.getSenha());
        usuarios.add(u);
    }

    public Usuario getUsuarioByUsername(String nome) {
        for(Usuario u : usuarios){
            if(u.getNome().equals(nome)){
                return u;
            }
        }
        
        return null;
    }
    
    
    
    
    
    
    
    
}
