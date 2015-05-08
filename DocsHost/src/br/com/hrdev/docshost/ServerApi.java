package br.com.hrdev.docshost;

import br.com.hrdev.docshost.Storage;
import br.com.hrdev.docshost.exceptions.CadastroException;
import br.com.hrdev.docshost.exceptions.LoginException;
import br.com.hrdev.shared.docs.api.Api;
import br.com.hrdev.shared.docs.exceptions.InputDataException;
import br.com.hrdev.shared.docs.models.Usuario;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author 180901954
 */
public class ServerApi extends UnicastRemoteObject implements Api, Serializable {

    public ServerApi() throws RemoteException {
        super();
    }

    @Override
    public Usuario login(Usuario usuario) throws RemoteException, InputDataException {
        try {
            usuario = Storage.getInstance().getUsuarioByLogin(usuario);
            
            if (usuario == null) {
                throw new LoginException("Usuario não encontrado");
            }
            
            return usuario;

        } catch (LoginException e) {
            throw new InputDataException(e.getMessage());
        }
    }

    @Override
    public void cadastro(Usuario usuario) throws RemoteException, InputDataException {
        ValidateDataHelper.checkCadastroInputData(usuario);
        
        try {
            Usuario u = Storage.getInstance().getUsuarioByUsername(usuario.getNome());
            if(u == null){
                Storage.getInstance().saveUsuario(usuario);
            } else {
                throw new CadastroException("Usuario já registrado");
            }
        } catch (CadastroException e) {
            throw new InputDataException(e.getMessage());
        }
    }

    
    
}
