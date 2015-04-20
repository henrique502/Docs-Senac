package br.com.hrdev.docsclient.components;

import br.com.hrdev.docsclient.Main;
import br.com.hrdev.shared.docs.Documento;
import br.com.hrdev.shared.docs.Usuario;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author henriqueschmidt
 */
public class DocsLista extends JList {

    public DocsLista() {
        super();

        setLayoutOrientation(JList.VERTICAL);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setCellRenderer(new ListaCellRenderer());
        
        
        DefaultListModel listModel = new DefaultListModel();
        setModel(listModel);
        
        Usuario u = new Usuario("Henrique");
        Documento doc = new Documento(u);
        listModel.addElement(doc);
        
        doc = new Documento(u);
        listModel.addElement(doc);
        
        doc = new Documento(u);
        listModel.addElement(doc);
        
        doc = new Documento(u);
        listModel.addElement(doc);
        
    }

    private class ListaCellRenderer extends JLabel implements ListCellRenderer {
        
        private final Color HIGHLIGHT_COLOR = new Color(237, 237, 237);
        private final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, 14);

        public ListaCellRenderer() {
            setOpaque(true);
            setForeground(Color.black);
            setIconTextGap(0);
            setBorder(new EmptyBorder(5, 5, 5, 5));
            setFont(DEFAULT_FONT);
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Documento doc = (Documento) value;
            
            String label = "<html>" + doc.getTitulo() + "<br><small>" + doc.getAutor().getNome() + "</small></html>";
            setText(label);
            
            Icon icon = new ImageIcon(Main.getInstance().getAssets("doc.png"));
            setIcon(icon);
            
            if (isSelected) {
                setBackground(HIGHLIGHT_COLOR);
            } else {
                setBackground(Color.white);
            }

            return this;
        }

    }

}
