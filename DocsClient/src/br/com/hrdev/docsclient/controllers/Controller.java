package br.com.hrdev.docsclient.controllers;

import br.com.hrdev.docsclient.views.Window;
import br.com.hrdev.shared.docs.api.Api;
import javax.swing.JOptionPane;

/**
 *
 * @author henriqueschmidt
 */
public abstract class Controller {
    
    protected static Api api = null;
    
    public Controller() {
        
    }
    
    public void showErro(String msg){
        JOptionPane.showMessageDialog(Window.getInstance(), msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showInfo(String msg){
        JOptionPane.showMessageDialog(Window.getInstance(), msg, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
