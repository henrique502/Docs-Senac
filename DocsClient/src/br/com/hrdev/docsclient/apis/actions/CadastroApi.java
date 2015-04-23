package br.com.hrdev.docsclient.apis.actions;

import br.com.hrdev.docsclient.apis.Api;
import br.com.hrdev.docsclient.apis.ApiAction;
import br.com.hrdev.docsclient.controllers.CadastroController;
import br.com.hrdev.shared.docs.Mensagem;
import br.com.hrdev.shared.docs.Usuario;

/**
 *
 * @author henriqueschmidt
 */
public class CadastroApi extends ApiAction {

    private final CadastroController controller;
    private final Usuario usuario;

    public CadastroApi(CadastroController controller, Usuario usuario) {
        this.controller = controller;
        this.usuario = usuario;
    }

    @Override
    public void execute(Api.Stream stream) {
        String response;
        try {
            stream.send(new Mensagem("cadastro", usuario));
            Mensagem msg = (Mensagem) stream.get();
            
            controller.enableView();
            if(msg.getComando().equals("sucesso")){
                controller.apiSucesso(msg.getDescricao());
            } else {
                controller.showErro(msg.getDescricao());
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}
