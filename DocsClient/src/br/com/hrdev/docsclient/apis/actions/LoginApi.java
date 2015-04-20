package br.com.hrdev.docsclient.apis.actions;

import br.com.hrdev.docsclient.apis.Api;
import br.com.hrdev.docsclient.apis.ApiAction;
import br.com.hrdev.docsclient.controllers.LoginController;
import br.com.hrdev.shared.docs.Mensagem;
import br.com.hrdev.shared.docs.Usuario;

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
            stream.send(new Mensagem("login", usuario));
            Mensagem msg = (Mensagem) stream.get();
            
            controller.enableView();
            if(msg.getComando().equals("sucesso")){
                controller.login((Usuario) msg.getValue());
            } else {
                controller.showErro(msg.getDescricao());
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
