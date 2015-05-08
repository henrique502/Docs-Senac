package br.com.hrdev.docsclient.controllers;

import br.com.hrdev.shared.docs.exceptions.InputDataException;
import br.com.hrdev.docsclient.views.CadastroView;
import br.com.hrdev.docsclient.views.Window;
import br.com.hrdev.shared.docs.models.Usuario;
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

    public void enableView() {
        view.setEnabled(true);
    }

    private class CadastroAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: InputDataException
            try {
                if (isSamePassword() == false) {
                    throw new InputDataException("As senhas digitadas não são iguais");
                }

                Usuario usuario = new Usuario(view.getInputUsuario().getText(), new String(view.getInputSenha().getPassword()));
                try {
                    view.setEnabled(false);
                    api.cadastro(usuario);
                    showInfo("Cadastro realizado com sucesso");
                    Window.getInstance().changeView(Window.ViewID.LOGIN);
                } catch (Exception ex) {
                    showErro(ex.getMessage());
                    view.setEnabled(true);
                    ex.printStackTrace();
                }

            } catch (InputDataException ex) {
                showErro(ex.getMessage());
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
