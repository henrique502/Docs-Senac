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
    private int currentIndex = -1;
    private ArrayList<Documento> documentos;

    public DashboardController(DashboardView view) {
        this.view = view;
    }

    public void init() {
        view.getSidebar().getLista().addListSelectionListener(new DocumentoSelecionado());
        view.getBtnNovo().addActionListener(new NovoDocumento());
        view.getBtnRemover().addActionListener(new RemoverDocumento());
        view.getBtnSalvar().addActionListener(new SalvarDocumento());

    }

    public void setDocumentos() {
        documentos = Window.getInstance().getUsuario().getDocumentos();
    }

    private void clear() {
        view.getBtnSalvar().setEnabled(false);
        view.getEditable().setEnabled(false);
        view.getEditable().setTitulo("");
        view.getEditable().setConteudo("");
        currentIndex = -1;
    }

    private class SalvarDocumento implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentIndex < 0) {
                return;
            }

            Documento doc = documentos.get(currentIndex);
            doc.setTitulo(view.getEditable().getTitulo());
            doc.setConteudo(view.getEditable().getConteudo());
            view.getSidebar().getLista().updateUI();
        }
    }

    private class NovoDocumento implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String titulo = JOptionPane.showInputDialog(Window.getInstance(), "Digite o título", "Sem título");
            if (titulo == null || titulo.equals("")) {
                return;
            }

            Documento doc = new Documento();
            doc.setTitulo(titulo);

            documentos.add(doc);
            view.getSidebar().updateContent();
        }
    }

    private class RemoverDocumento implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = view.getSidebar().getLista().getSelectedIndex();
            if (index < 0) {
                return;
            }
            if (currentIndex == index) {
            }
            //TODO: Vereficar se é o dumento aber atual;

            documentos.remove(index);
            view.getSidebar().updateContent();
        }
    }

    private class DocumentoSelecionado implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            int index = view.getSidebar().getLista().getSelectedIndex();
            if (currentIndex == index) {
                return;
            }
            if (index < 0) {
                clear();
                return;
            };

            currentIndex = index;

            Documento doc = documentos.get(index);
            if (doc == null) {
                return;
            }

            view.getBtnSalvar().setEnabled(true);
            view.getEditable().setEnabled(true);
            view.getEditable().setTitulo(doc.getTitulo());
            view.getEditable().setConteudo(doc.getConteudo());
        }

    }

}
