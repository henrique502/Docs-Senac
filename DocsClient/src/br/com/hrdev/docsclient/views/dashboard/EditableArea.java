package br.com.hrdev.docsclient.views.dashboard;

import br.com.hrdev.docsclient.views.DashboardView;
import br.com.hrdev.shared.docs.Documento;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author henriqueschmidt
 */
public class EditableArea extends JPanel {
    
    private final DashboardView view;
    private Documento doc;
    private final JTextArea texto;
    private final JTextField titulo;

    
    public EditableArea(DashboardView view) {
        this.view = view;
        setLayout(new BorderLayout(5, 5));
        
        Font font = new Font("Arial", Font.PLAIN, 14);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        border = BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        /* Panel Content */
        JPanel content = new JPanel(new BorderLayout(5, 5));
        
        /* Titulo Content */
        titulo = new JTextField();
        titulo.setFont(font);
        titulo.setBorder(border);
        content.add(titulo, BorderLayout.NORTH);
        
        /* Texto Content */
        texto = new JTextArea();
        texto.setBorder(border);
        texto.setFont(font);
        texto.setLineWrap(true);
        texto.setWrapStyleWord(true);
        content.add(texto, BorderLayout.CENTER);
        
        /* Scroll Content */
        JScrollPane scrollContent = new JScrollPane(content, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollContent.setBorder(null);
        add(scrollContent, BorderLayout.CENTER);
        
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        titulo.setEnabled(enabled);
        texto.setEnabled(enabled);
    }
    
    public String getTitulo(){
        return this.titulo.getText();
    }
    
    public void setTitulo(String titulo){
        this.titulo.setText(titulo);
    }
    
    public String getConteudo(){
        return this.texto.getText();
    }
    
    public void setConteudo(String conteudo){
        this.texto.setText(conteudo);
    }
}