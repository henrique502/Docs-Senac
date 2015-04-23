package br.com.hrdev.docsclient.views;

import br.com.hrdev.docsclient.controllers.CadastroController;
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
public class CadastroView extends View {
    
    private JButton btnCadastrar;
    private JButton btnVoltar;
    private JLabel labelUsuario;
    private JLabel labelSenha;
    private JLabel labelRepita;
    private JPasswordField inputSenha;
    private JPasswordField inputRepita;
    private JTextField inputUsuario;
    private final CadastroController controller;

    public CadastroView(){
        controller = new CadastroController(this);
        initComponents();
        controller.init();
    }
 
    private void initComponents() {

        labelUsuario = new javax.swing.JLabel();
        inputUsuario = new javax.swing.JTextField();
        inputSenha = new javax.swing.JPasswordField();
        inputRepita = new javax.swing.JPasswordField();
        labelSenha = new javax.swing.JLabel();
        labelRepita = new javax.swing.JLabel();
        btnCadastrar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();

        labelUsuario.setText("Usuário");
        labelSenha.setText("Senha");
        labelRepita.setText("Repita");
        
        inputUsuario.setText("");
        inputSenha.setText("");
        inputRepita.setText("");
        
        btnCadastrar.setText("Cadastrar");
        btnVoltar.setText("Voltar");
        
        /* Centraliza */
        JPanel panel = new JPanel();
        setLayout(new GridBagLayout());
        add(panel, new GridBagConstraints());
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputUsuario))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVoltar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
                        .addComponent(btnCadastrar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelRepita, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(6, 6, 6)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputSenha)
                            .addComponent(inputRepita)))))
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inputUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSenha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputRepita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRepita))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrar)
                    .addComponent(btnVoltar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }                    

    @Override
    protected void actived() {
        inputUsuario.setText("");
        inputSenha.setText("");
        inputRepita.setText("");
        setEnabled(true);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        inputUsuario.setEnabled(enabled);
        inputSenha.setEnabled(enabled);
        inputRepita.setEnabled(enabled);
        btnCadastrar.setEnabled(enabled);
        btnVoltar.setEnabled(enabled);
    }

    public JPasswordField getInputSenha() {
        return inputSenha;
    }

    public JPasswordField getInputRepita() {
        return inputRepita;
    }

    public JTextField getInputUsuario() {
        return inputUsuario;
    }

    public JButton getBtnCadastrar() {
        return btnCadastrar;
    }

    public JButton getBtnVoltar() {
        return btnVoltar;
    }
}