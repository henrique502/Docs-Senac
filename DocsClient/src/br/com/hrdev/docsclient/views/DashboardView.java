package br.com.hrdev.docsclient.views;

import br.com.hrdev.docsclient.Main;
import br.com.hrdev.docsclient.controllers.DashboardController;
import br.com.hrdev.docsclient.views.dashboard.EditableArea;
import br.com.hrdev.docsclient.views.dashboard.Sidebar;
import java.awt.BorderLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

/**
 *
 * @author henriqueschmidt
 */
public class DashboardView extends View {
    
    private final DashboardController controller;
    private Sidebar sidebar;
    private EditableArea editable;
    private JButton btnNovo;
    private JButton btnRemover;
    private JButton btnSalvar;

    public DashboardView() {
        controller = new DashboardController(this);
        initComponents();
        controller.init();
    }

    private void initComponents() {
        setLayout(new BorderLayout(5, 5));
        this.sidebar = new Sidebar(this);
        this.editable = new EditableArea(this);
        
        add(sidebar, BorderLayout.WEST);
        add(editable, BorderLayout.CENTER);
        Icon icon = null;
        
        /* Toolbar */
        JToolBar toolBar = new JToolBar(JToolBar.HORIZONTAL);
        add(toolBar, BorderLayout.NORTH);
        toolBar.setFloatable(false);
        toolBar.setRollover(true);
        
        /* Novo Documento */
        icon = new ImageIcon(Main.getInstance().getAssets("add.png"));
        btnNovo = new JButton("Novo", icon);
        toolBar.add(btnNovo);
        
        /* Remover Documento */
        icon = new ImageIcon(Main.getInstance().getAssets("delete.png"));
        btnRemover = new JButton("Remover", icon);
        toolBar.add(btnRemover);
        
        toolBar.add(new JSeparator(SwingConstants.VERTICAL));
        
        /* Salvar Documento */
        icon = new ImageIcon(Main.getInstance().getAssets("save.png"));
        btnSalvar = new JButton("Salvar", icon);
        toolBar.add(btnSalvar);
        
        
        btnNovo.setEnabled(false);
        btnRemover.setEnabled(false);
        btnSalvar.setEnabled(false);
        
        btnNovo.setFocusPainted(false);
        btnRemover.setFocusPainted(false);
        btnSalvar.setFocusPainted(false);
    }
    
    
    @Override
    protected void actived() {
        sidebar.updateContent();
        editable.setEnabled(false);
        btnNovo.setEnabled(true);
        controller.setDocumentos();
    }

    public DashboardController getController() {
        return controller;
    }

    public Sidebar getSidebar() {
        return sidebar;
    }

    public EditableArea getEditable() {
        return editable;
    }

    public JButton getBtnNovo() {
        return btnNovo;
    }

    public JButton getBtnRemover() {
        return btnRemover;
    }

    public JButton getBtnSalvar() {
        return btnSalvar;
    }
}
