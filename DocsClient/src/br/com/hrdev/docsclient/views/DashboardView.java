package br.com.hrdev.docsclient.views;

import br.com.hrdev.docsclient.controllers.DashboardController;
import br.com.hrdev.docsclient.views.dashboard.EditableArea;
import br.com.hrdev.docsclient.views.dashboard.Sidebar;
import java.awt.BorderLayout;

/**
 *
 * @author henriqueschmidt
 */
public class DashboardView extends View {
    
    private final DashboardController controller;
    private Sidebar sidebar;
    private EditableArea editable;

    public DashboardView() {
        controller = new DashboardController(this);
        initComponents();
        controller.init();
    }

    

    private void initComponents() {
        setLayout(new BorderLayout(0, 0));
        this.sidebar = new Sidebar(this);
        this.editable = new EditableArea(this);
        
        add(sidebar, BorderLayout.WEST);
        add(editable, BorderLayout.CENTER);
    }
    
    
    @Override
    protected void actived() {
        sidebar.updateContent();
        editable.setEnabled(false);
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
    
    
}
