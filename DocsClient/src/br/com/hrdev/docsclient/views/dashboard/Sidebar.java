package br.com.hrdev.docsclient.views.dashboard;

import br.com.hrdev.docsclient.components.DocsLista;
import br.com.hrdev.docsclient.views.DashboardView;
import br.com.hrdev.docsclient.views.Window;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author henriqueschmidt
 */
public class Sidebar extends JPanel {
    private final DocsLista lista;
    private final DashboardView view;

    public Sidebar(DashboardView view) {
        this.view = view;
        setLayout(new BorderLayout(0, 0));
        setPreferredSize(new Dimension(250, Window.HEIGHT));
        
        /* JList Sidebar */
        lista = new DocsLista();
        
        /* Scroll Sidebar */
        JScrollPane scrollSidebar = new JScrollPane(lista, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollSidebar.setBorder(null);
        add(scrollSidebar, BorderLayout.CENTER); 
    }

    public DocsLista getLista() {
        return lista;
    }

    public void updateContent() {
        lista.updateContent();
    }
}
