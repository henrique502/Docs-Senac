package br.com.hrdev.docsclient.views.dashboard;

import br.com.hrdev.docsclient.Main;
import br.com.hrdev.docsclient.components.DocsLista;
import br.com.hrdev.docsclient.views.DashboardView;
import br.com.hrdev.docsclient.views.Window;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author henriqueschmidt
 */
public class Sidebar extends JPanel {
    private final JButton btnAdicionarSidebar;
    private final JButton btnRemoverSidebar;
    private final DocsLista lista;
    private final DashboardView view;

    public Sidebar(DashboardView view) {
        this.view = view;
        setLayout(new BorderLayout(0, 0));
        setPreferredSize(new Dimension(250, Window.HEIGHT));

        /* Buttons Sidebar */
        JPanel buttonsSidebar = new JPanel(new GridLayout(1, 2));
        add(buttonsSidebar, BorderLayout.NORTH);
        
        /* Adicionar Button Sidebar */
        ImageIcon icon = new ImageIcon(Main.getInstance().getAssets("add.png"));
        btnAdicionarSidebar = new JButton("Adicionar", icon);
        buttonsSidebar.add(btnAdicionarSidebar);
        
        /* Remover Button Sidebar */
        icon = new ImageIcon(Main.getInstance().getAssets("delete.png"));
        btnRemoverSidebar = new JButton("Remover", icon);
        buttonsSidebar.add(btnRemoverSidebar);
        btnRemoverSidebar.setEnabled(false);
        
        /* JList Sidebar */
        lista = new DocsLista();
        
        /* Scroll Sidebar */
        JScrollPane scrollSidebar = new JScrollPane(lista, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollSidebar.setBorder(null);
        add(scrollSidebar, BorderLayout.CENTER); 
    }

    public JButton getBtnAdicionar() {
        return btnAdicionarSidebar;
    }

    public JButton getBtnRemover() {
        return btnRemoverSidebar;
    }

    public DocsLista getLista() {
        return lista;
    }

    public void updateContent() {
        lista.updateContent();
        
        
    }
}
