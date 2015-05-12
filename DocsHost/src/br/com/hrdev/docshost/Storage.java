package br.com.hrdev.docshost;

import br.com.hrdev.shared.docs.exceptions.InputDataException;
import br.com.hrdev.shared.docs.models.Documento;
import br.com.hrdev.shared.docs.models.Usuario;
import java.util.ArrayList;

/**
 *
 * @author henriqueschmidt
 */
public class Storage {
    
    private static Storage instance = null;
    
    private final ArrayList<Usuario> usuarios;
    private final ArrayList<Documento> documentos;
    private Long usuarioId = 1l;
    private Long documentoId = 1l;
    
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
        u.setId(usuarioId++);
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
    
    public Usuario getUsuarioById(Long id) {
        for(Usuario u : usuarios){
            if(u.getId().equals(id)){
                return u;
            }
        }
        
        return null;
    }
    
    /* Documentos */
    public Documento saveDocumento(Usuario usuario, Documento documento) {
        Documento doc = new Documento();
        doc.setId(documentoId++);
        doc.setAutor(usuario.getId());
        doc.setTitulo(documento.getTitulo());
        doc.setConteudo(documento.getConteudo());
        getUsuarioById(usuario.getId()).getDocumentos().add(doc);
        return doc;
    }

    public ArrayList<Documento> getDocumentosByAutor(Long id){
         return getUsuarioById(id).getDocumentos();
    }
    
    public void updateDocumento(Documento documento) throws InputDataException {
        ArrayList<Documento> docs = getUsuarioById(documento.getAutor()).getDocumentos();
        for(Documento doc : docs){
            if(doc.getId().equals(documento.getId())){
                doc.setTitulo(documento.getTitulo());
                doc.setConteudo(documento.getConteudo());
                return;
            }
        }
        
        throw new InputDataException("falha ao salvar documento");
    } 
}
