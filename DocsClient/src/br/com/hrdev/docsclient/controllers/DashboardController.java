package br.com.hrdev.docsclient.controllers;

import static br.com.hrdev.docsclient.controllers.Controller.api;
import br.com.hrdev.docsclient.views.DashboardView;
import br.com.hrdev.docsclient.views.Window;
import br.com.hrdev.shared.docs.exceptions.InputDataException;
import br.com.hrdev.shared.docs.models.Documento;
import br.com.hrdev.shared.docs.models.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
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
    private Timer timer = null;
    private boolean timeRunning = false;
    public static final long TEMPO = (1000 * 5);

    public DashboardController(DashboardView view) {
        this.view = view;
    }

    public void init() {
        view.getSidebar().getLista().addListSelectionListener(new DocumentoSelecionado());
        view.getBtnNovo().addActionListener(new NovoDocumento());
        view.getBtnRemover().addActionListener(new RemoverDocumento());
        view.getBtnSalvar().addActionListener(new SalvarDocumento());
        UpdateServerDocumento updateServer = new UpdateServerDocumento();
        view.getEditable().getTituloField().addKeyListener(updateServer);
        view.getEditable().getTextoField().addKeyListener(updateServer);
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

    private void updateDoc() {
        try {
            if (currentIndex < 0) {
                return;
            }

            Documento doc = documentos.get(currentIndex);
            doc.setTitulo(view.getEditable().getTitulo());
            doc.setConteudo(view.getEditable().getConteudo());
            api.updateDocumento(doc);
            view.getSidebar().getLista().updateUI();
        } catch (RemoteException | InputDataException ex) {
            showErro(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void configUpdaterTimer() {
        if (timer == null) {
            timer = new Timer();
            TimerTask tarefa = new TimerTask() {
                @Override
                public void run() {
                    try {
                        if(timeRunning == false){
                            timeRunning = true;
                            updateClienteDocs();
                            timeRunning = false;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
        }
    }

    private void updateClienteDocs() {
        try {
            Usuario u = Window.getInstance().getUsuario();
            u.setDocumentos(api.getDocumentosByUsuario(u));
            setDocumentos();
            view.getSidebar().updateContent();
            if(currentIndex >= 0){
                view.getSidebar().getLista().setSelectedIndex(currentIndex);
                openDocument(currentIndex);
            }
        } catch (RemoteException | InputDataException ex) {
            ex.printStackTrace();
        }
    }
    
    private void openDocument(int index) {
        if(index < 0) return;
        
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

    private class SalvarDocumento implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            updateDoc();
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

            try {
                doc = api.criarDocumento(Window.getInstance().getUsuario(), doc);
                documentos.add(doc);
                view.getSidebar().updateContent();
            } catch (RemoteException | InputDataException ex) {
                showErro(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    private class RemoverDocumento implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            return;
        }
    }

    private class DocumentoSelecionado implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            int index = view.getSidebar().getLista().getSelectedIndex();
            openDocument(index);
        }

    }

    private class UpdateServerDocumento implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            updateDoc();
        }
    }
}
