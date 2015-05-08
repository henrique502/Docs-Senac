package br.com.hrdev.shared.docs.api;

import br.com.hrdev.shared.docs.exceptions.InputDataException;
import br.com.hrdev.shared.docs.models.Usuario;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author 180901954
 */
public interface Api extends Remote {
    
    public Usuario login(Usuario usuario) throws RemoteException, InputDataException;
    public void cadastro(Usuario usuario) throws RemoteException, InputDataException;
    
}
