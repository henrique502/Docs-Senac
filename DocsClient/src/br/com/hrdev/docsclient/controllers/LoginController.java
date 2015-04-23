package br.com.hrdev.docsclient.controllers;

import br.com.hrdev.docsclient.apis.Api;
import br.com.hrdev.docsclient.apis.ApiAction;
import br.com.hrdev.docsclient.apis.actions.LoginApi;
import br.com.hrdev.docsclient.views.LoginView;
import br.com.hrdev.docsclient.views.Window;
import br.com.hrdev.shared.docs.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author henriqueschmidt
 */
public class LoginController extends Controller {

    private final LoginView view;
    private final LoginController self = this;

    public LoginController(LoginView view) {
        this.view = view;
    }

    public void init() {
        view.getBtnNovaConta().addActionListener(new NovaContaAction());
        view.getBtnLogin().addActionListener(new LoginAction());
    }
    
    public void login(Usuario usuario){
        Window.getInstance().setStatusText("Bem Vindo " + usuario.getNome());
        
        Window.getInstance().setUsuario(usuario);
        Window.getInstance().changeView(Window.ViewID.DASHBOARD);
    }

    public void enableView() {
        Window.getInstance().setStatusText("Conectado");
        view.setEnabled(true);
    }

    private class NovaContaAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Window.getInstance().changeView(Window.ViewID.CADASTRO);
        }
    }

    private class LoginAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.setEnabled(false);
            Window.getInstance().setStatusText("Logando...");

            Usuario usuario = new Usuario(view.getInputUsername().getText(), new String(view.getInputPassword().getPassword()));
            ApiAction action = new LoginApi(self, usuario);
            
            try {
                Api.getInstance().execute(action);
            } catch (Exception ex) {
                Window.getInstance().setStatusText(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
