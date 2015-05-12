package br.com.hrdev.docsclient.controllers;

import static br.com.hrdev.docsclient.controllers.Controller.api;
import br.com.hrdev.docsclient.views.LoginView;
import br.com.hrdev.docsclient.views.Window;
import br.com.hrdev.shared.docs.models.Documento;
import br.com.hrdev.shared.docs.models.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    
    private void login(Usuario usuario){
        Window w = Window.getInstance();
        w.setStatusText("Bem Vindo " + usuario.getNome());
        w.setUsuario(usuario);
        w.changeView(Window.ViewID.DASHBOARD);
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
            // TODO: InputDataException
            view.setEnabled(false);
            Window.getInstance().setStatusText("Logando...");

            Usuario usuario = new Usuario(view.getInputUsername().getText(), new String(view.getInputPassword().getPassword()));
            try {
                usuario = api.login(usuario);
                login(usuario);
            } catch (Exception ex) {
                Window.getInstance().setStatusText(ex.getMessage());
                ex.printStackTrace();
            }
            
            
        }
    }
}
