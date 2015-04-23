package br.com.hrdev.docsclient.controllers;

import br.com.hrdev.docsclient.views.DashboardView;
import br.com.hrdev.docsclient.views.Window;
import br.com.hrdev.shared.docs.Documento;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author henriqueschmidt
 */
public class DashboardController extends Controller {
    
    private final DashboardView view;
    
    public DashboardController(DashboardView view) {
        this.view = view;
    }

    public void init() {
        view.getSidebar().getLista().addListSelectionListener(new DocumentoSelecionado());
        view.getSidebar().getBtnAdicionar().addActionListener(new NovoDocumento());
        view.getSidebar().getBtnRemover().addActionListener(new RemoverDocumento());

    }
    
    private class NovoDocumento implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String titulo = JOptionPane.showInputDialog(Window.getInstance(), "Digite o título", "Sem título");
            if(titulo == null || titulo.equals("")) return;
            
            Documento doc = new Documento(Window.getInstance().getUsuario());
            doc.setTitulo(titulo);
            
            ArrayList<Documento> documentos = Window.getInstance().getUsuario().getDocumentos();
            documentos.add(doc);
            view.getSidebar().updateContent();
        }
    }
    
    private class RemoverDocumento implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = view.getSidebar().getLista().getSelectedIndex();
            if(index < 0) return;
            
            //TODO: Vereficar se é o dumento aber atual;
            
            ArrayList<Documento> documentos = Window.getInstance().getUsuario().getDocumentos();
            
            documentos.remove(index);
            view.getSidebar().updateContent();
        }
    }
    
    private class DocumentoSelecionado implements ListSelectionListener {
        
        private int last = -1;
        
        @Override
        public void valueChanged(ListSelectionEvent e){
            int index = view.getSidebar().getLista().getSelectedIndex();
            if(last == index) return;
            
            ArrayList<Documento> documentos = Window.getInstance().getUsuario().getDocumentos();
            Documento doc = documentos.get(index);
            if(doc == null) return;
            
            view.getEditable().setDoc(doc);
        }
        
    }

}
