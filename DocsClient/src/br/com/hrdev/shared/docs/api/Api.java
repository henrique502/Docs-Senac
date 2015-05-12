package br.com.hrdev.shared.docs.api;

import br.com.hrdev.shared.docs.exceptions.InputDataException;
import br.com.hrdev.shared.docs.models.Documento;
import br.com.hrdev.shared.docs.models.Usuario;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Api extends Remote {
    
    public Usuario login(Usuario usuario) throws RemoteException, InputDataException;
    public Usuario logout(Usuario usuario) throws RemoteException, InputDataException;
    public void cadastro(Usuario usuario) throws RemoteException, InputDataException;
    public void criarDocumento(Usuario usuario, Documento documento) throws RemoteException, InputDataException;
    public void compartilharDocumento() throws RemoteException, InputDataException;
    public void salvarDocumento() throws RemoteException, InputDataException;
    public void excluirDocumento() throws RemoteException, InputDataException;
    
}
