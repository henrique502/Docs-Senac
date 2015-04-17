package br.com.hrdev.docshost.api;

import br.com.hrdev.docshost.entities.Usuario;
import br.com.hrdev.docshost.server.Connection;
import java.io.IOException;

/**
 *
 * @author henriqueschmidt
 */
public class Api {

    public void login(Connection.Stream stream) throws IOException, ClassNotFoundException {
        stream.log("ok");
        stream.w.println("ok");
        
        stream.log("recebendo Class Usuario");
        Usuario usuario = (Usuario) stream.ro.readObject();
        System.out.println(usuario.getNome());
        
        stream.log("ok");
        stream.w.println("ok");
    }

    public void notfound(Connection.Stream stream) {
        stream.w.println("Comando nao encontrado");
    }

}
