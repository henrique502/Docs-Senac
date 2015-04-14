package br.com.hrdev.docsclient.apis.actions;

import br.com.hrdev.docsclient.apis.ApiAction;
import br.com.hrdev.docsclient.apis.ApiTalk;
import br.com.hrdev.docsclient.controllers.LoginController;
import br.com.hrdev.docsclient.entities.Usuario;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 *
 * @author henriqueschmidt
 */
public class LoginApi extends ApiAction {
    
    private final LoginController controller;
    private final Usuario usuario;

    public LoginApi(LoginController controller, Usuario usuario) {
        this.controller = controller;
        this.usuario = usuario;
    }

    @Override
    public void execute(InputStream in, OutputStream out) {
        String response;
        try {
            ObjectOutputStream writerObject = new ObjectOutputStream(out);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            PrintStream writer = new PrintStream(out);
            
            writer.println(ApiTalk.LOGIN);
            response = reader.readLine();
            if(response.equals(ApiTalk.OK)){
                writerObject.writeObject(usuario);
                
                response = reader.readLine();
                System.out.println(response);
            } else {
                System.err.println(response);
            }
            
            
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
