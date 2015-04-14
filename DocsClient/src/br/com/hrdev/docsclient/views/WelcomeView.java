package br.com.hrdev.docsclient.views;

import br.com.hrdev.docsclient.controllers.WelcomeController;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author henriqueschmidt
 */
public class WelcomeView extends View {

    private JProgressBar progressBar;
    private final WelcomeController controller;

    public WelcomeView(){
        controller = new WelcomeController(this);
        initComponents();
        controller.init();
    }
    
    private void initComponents(){
        progressBar = new JProgressBar();
        progressBar.setVisible(true);
        progressBar.setIndeterminate(true);
        progressBar.setPreferredSize(new Dimension(460, 40));
        
        JPanel panel = new JPanel();
        setLayout(new GridBagLayout());
        add(panel, new GridBagConstraints());
        
        panel.add(progressBar);
    }

    @Override
    protected void actived() {
        controller.initApi();
    }
}
