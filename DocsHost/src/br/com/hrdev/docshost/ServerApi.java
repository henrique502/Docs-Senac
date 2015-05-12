package br.com.hrdev.docshost;

import br.com.hrdev.docshost.exceptions.CadastroException;
import br.com.hrdev.docshost.exceptions.LoginException;
import br.com.hrdev.shared.docs.api.Api;
import br.com.hrdev.shared.docs.exceptions.InputDataException;
import br.com.hrdev.shared.docs.models.Documento;
import br.com.hrdev.shared.docs.models.Usuario;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


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

    @Override
    public Documento criarDocumento(Usuario usuario, Documento documento) throws RemoteException, InputDataException {
        usuario = Storage.getInstance().getUsuarioById(usuario.getId());
        
        if(usuario == null)
            throw new InputDataException("usuario e null");
            
        if(documento == null)
            throw new InputDataException("documento e null");
                
        Documento doc = Storage.getInstance().saveDocumento(usuario, documento);
            
        return doc;
    }

    @Override
    public void updateDocumento(Documento documento) throws RemoteException, InputDataException {            
        if(documento == null)
            throw new InputDataException("documento e null");
        
        Storage.getInstance().updateDocumento(documento);
    }

   
    @Override
    public ArrayList<Documento> getDocumentosByUsuario(Usuario usuario) throws RemoteException, InputDataException {
        usuario = Storage.getInstance().getUsuarioById(usuario.getId());
        
        if(usuario == null)
            throw new InputDataException("usuario e null");
        
        return usuario.getDocumentos();
    }
    
    @Override
    public void excluirDocumento(Long id) throws RemoteException, InputDataException {}
    
    @Override
    public void compartilharDocumento(String username) throws RemoteException, InputDataException {}
}
