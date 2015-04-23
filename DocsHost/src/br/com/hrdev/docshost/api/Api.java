package br.com.hrdev.docshost.api;

import br.com.hrdev.docshost.Storage;
import br.com.hrdev.docshost.exceptions.CadastroException;
import br.com.hrdev.docshost.exceptions.LoginException;
import br.com.hrdev.docshost.server.Connection;
import br.com.hrdev.shared.Shared;
import br.com.hrdev.shared.docs.Mensagem;
import br.com.hrdev.shared.docs.Usuario;
import java.io.IOException;

/**
 *
 * @author henriqueschmidt
 */
public class Api {
    
    private static final Storage storage = Storage.getInstance();
    
    public void notfound(Connection.Stream stream) {
        
    }

    public void login(Connection.Stream stream, Shared value) throws IOException {
        try {
            Usuario usuario = (Usuario) value;

            usuario = storage.getUsuarioByLogin(usuario);
            
            if (usuario == null) {
                throw new LoginException("Usuario não encontrado");
            }
            
            stream.send(new Mensagem("sucesso", usuario));

        } catch (LoginException e) {
            stream.send(new Mensagem(false, e.getMessage()));
        }

    }

    public void cadastro(Connection.Stream stream, Shared value) throws IOException {
        try {
            Usuario usuario = (Usuario) value;
            
            Usuario u = storage.getUsuarioByUsername(usuario.getNome());
            if(u == null){
                storage.saveUsuario(usuario);
            } else {
                throw new CadastroException("Usuario já registrado");
            }

            stream.send(new Mensagem(true, "Cadastro efetuado com sucesso"));

        } catch (CadastroException e) {
            stream.send(new Mensagem(false, e.getMessage()));
        }
    }
}
