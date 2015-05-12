package br.com.hrdev.shared.docs.api;

import br.com.hrdev.shared.docs.exceptions.InputDataException;
import br.com.hrdev.shared.docs.models.Documento;
import br.com.hrdev.shared.docs.models.Usuario;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author 180901954
 */
public interface Api extends Remote {
    
    public Usuario login(Usuario usuario) throws RemoteException, InputDataException;
    public void cadastro(Usuario usuario) throws RemoteException, InputDataException;
    public Documento criarDocumento(Usuario usuario, Documento documento) throws RemoteException, InputDataException;
    public void updateDocumento(Documento documento) throws RemoteException, InputDataException;
    public ArrayList<Documento> getDocumentosByUsuario(Usuario usuario) throws RemoteException, InputDataException;
    
    public void compartilharDocumento(String username) throws RemoteException, InputDataException;
    public void excluirDocumento(Long id) throws RemoteException, InputDataException;
}