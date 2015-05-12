package br.com.hrdev.docsclient.views;

import br.com.hrdev.docsclient.controllers.LoginController;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author henriqueschmidt
 */
public class LoginView extends View {

    private JButton btnLogin;
    private JButton btnNovaConta;
    private JPasswordField inputPassword;
    private JTextField inputUsername;
    private JLabel labelSenha;
    private JLabel labelUsuario;
    
    private final LoginController controller;

    public LoginView() {
        controller = new LoginController(this);
        initComponents();
        controller.init();
    }

    private void initComponents() {

        labelUsuario = new javax.swing.JLabel();
        labelSenha = new javax.swing.JLabel();
        inputUsername = new javax.swing.JTextField();
        inputPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnNovaConta = new javax.swing.JButton();

        labelUsuario.setText("Usuário");
        labelSenha.setText("Senha");

        btnLogin.setText("Login");
        btnNovaConta.setText("Criar nova conta");
        
        JPanel panel = new JPanel();
        setLayout(new GridBagLayout());
        add(panel, new GridBagConstraints());
        
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(labelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(labelSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(inputUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                                                .addComponent(inputPassword)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 151, Short.MAX_VALUE)
                                        .addComponent(btnNovaConta)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnLogin)))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(inputUsername)
                                .addComponent(labelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelSenha)
                                .addComponent(inputPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnLogin)
                                .addComponent(btnNovaConta)))
        );
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JButton getBtnNovaConta() {
        return btnNovaConta;
    }

    public JPasswordField getInputPassword() {
        return inputPassword;
    }

    public JTextField getInputUsername() {
        return inputUsername;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        btnLogin.setEnabled(enabled);
        btnNovaConta.setEnabled(enabled);
        inputPassword.setEnabled(enabled);
        inputUsername.setEnabled(enabled);
    }

    @Override
    protected void actived(){
        inputUsername.setText("");
        inputPassword.setText("");
        setEnabled(true);
    }
}
