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
    public Usuario logout(Usuario usuario) throws RemoteException, InputDataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void criarDocumento(Usuario usuario, String titulo, String conteudo) throws RemoteException, InputDataException {
        Documento documento = new Documento();
        documento.setAutor(usuario);
        documento.setTitulo(titulo);
        documento.setConteudo(conteudo);
    }

    @Override
    public void compartilharDocumento() throws RemoteException, InputDataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void salvarDocumento() throws RemoteException, InputDataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluirDocumento() throws RemoteException, InputDataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
