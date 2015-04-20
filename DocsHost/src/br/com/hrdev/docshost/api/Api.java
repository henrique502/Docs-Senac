package br.com.hrdev.docshost.api;

import br.com.hrdev.docshost.Storage;
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
            Usuario usuario = null;

            if (value instanceof Usuario) {
                usuario = (Usuario) value;
            }

            if (usuario == null) {
                throw new LoginException("Classe usuario requerida");
            }
            
            usuario = storage.getUsuarioByLogin(usuario);
            
            if (usuario == null) {
                throw new LoginException("Usuario não encontrado");
            }
            
            stream.send(new Mensagem("sucesso", usuario));

        } catch (LoginException e) {
            stream.send(new Mensagem(false, e.getMessage()));
        }

    }

}
