package br.com.hrdev.docsclient.apis.actions;

import br.com.hrdev.docsclient.apis.Api;
import br.com.hrdev.docsclient.apis.ApiAction;
import br.com.hrdev.docsclient.controllers.LoginController;
import br.com.hrdev.docsclient.entities.Usuario;

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
    public void execute(Api.Stream stream) {
        String response;
        try {
            stream.w.println("login");
            response = stream.r.readLine();
            
            System.out.println(response);
            
            if(response.equals("ok")){
                stream.wo.writeObject(usuario);
                
                response = stream.r.readLine();
                System.out.println(response);
            } else {
                System.err.println(response);
            }
            
            
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
