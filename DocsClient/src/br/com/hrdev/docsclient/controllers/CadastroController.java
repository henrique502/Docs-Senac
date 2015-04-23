package br.com.hrdev.docsclient.controllers;

import br.com.hrdev.docsclient.apis.Api;
import br.com.hrdev.docsclient.apis.actions.CadastroApi;
import br.com.hrdev.docsclient.exceptions.InputDataException;
import br.com.hrdev.docsclient.views.CadastroView;
import br.com.hrdev.docsclient.views.Window;
import br.com.hrdev.shared.docs.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author henriqueschmidt
 */
public class CadastroController extends Controller {

    private final CadastroView view;
    private final CadastroController self;

    public CadastroController(CadastroView view) {
        this.view = view;
        this.self = this;
    }

    public void init() {
        view.getBtnCadastrar().addActionListener(new CadastroAction());
        view.getBtnVoltar().addActionListener(new VoltarAction());

    }

    private boolean isSamePassword() {
        String password = new String(view.getInputSenha().getPassword());
        String repita = new String(view.getInputRepita().getPassword());

        return password.equals(repita);
    }

    private boolean hasSizePassword() {
        String password = new String(view.getInputSenha().getPassword());
        int size = password.length();

        return (size <= 12 && size >= 6);
    }

    private boolean hasSizeUsername() {
        String usuario = view.getInputUsuario().getText();
        int size = usuario.length();

        return (size <= 20 && size >= 3);
    }

    public void apiSucesso(String msg) {
        showInfo(msg);
        Window.getInstance().changeView(Window.ViewID.LOGIN);
    }

    public void enableView() {
        view.setEnabled(true);
    }

    private class CadastroAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                checkInputData();
                
                Usuario usuario = new Usuario(view.getInputUsuario().getText(), new String(view.getInputSenha().getPassword()));
                CadastroApi action = new CadastroApi(self, usuario);
            
                try {
                    view.setEnabled(false);
                    Api.getInstance().execute(action);
                } catch (Exception ex){
                    showErro(ex.getMessage());
                    view.setEnabled(true);
                    ex.printStackTrace();
                }

            } catch (InputDataException ex) {
                showErro(ex.getMessage());
            }
        }

        private void checkInputData() throws InputDataException {
            if (isSamePassword() == false) {
                throw new InputDataException("As senhas digitadas não são iguais");
            }

            if (hasSizePassword() == false) {
                throw new InputDataException("A senha deve ter de 6 a 12 caracteres");
            }

            if (hasSizeUsername() == false) {
                throw new InputDataException("O nome deve ter de 3 a 20 caracteres");
            }
        }
    }

    private class VoltarAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Window.getInstance().changeView(Window.ViewID.LOGIN);
        }
    }

}
