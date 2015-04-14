package br.com.hrdev.docsclient.views;

import br.com.hrdev.docsclient.Main;
import br.com.hrdev.docsclient.components.DocsLista;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author henriqueschmidt
 */
public class ApplicationFrame extends JFrame {
    
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    public static final Color BACKGROUND_COLOR = new Color(238, 238, 238);
    private static ApplicationFrame instance = null;
    
    private DocsLista lista;
    private JButton btnAdicionarSidebar;
    private JButton btnRemoverSidebar;
    private JTextArea texto;
    private JTextField titulo;
    
    public ApplicationFrame(){
        instance = this;
        
        setAtributos();
        setComponentes();
        
        add(new LoginView());
    }
    
    public static ApplicationFrame getInstance(){
        return instance;
    }

    private void setAtributos() {
        Dimension windowSize = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(windowSize);
        setMinimumSize(windowSize);
        
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        setMaximumSize(new Dimension(gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight()));
        
        setJMenuBar(new JMenuBar());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBackground(BACKGROUND_COLOR);
        
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            
                dispose();
                System.exit(0);
            }
        });
        
        setLayout(new BorderLayout(5, 5));
        
    }

    private void setComponentes() {
        Icon icon = null;
        Font font = new Font("Arial", Font.PLAIN, 14);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        border = BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        /* Panel Sidebar */
        JPanel sidebar = new JPanel(new BorderLayout(0, 0));
        sidebar.setPreferredSize(new Dimension(250, HEIGHT));
        add(sidebar, BorderLayout.WEST);

        /* Buttons Sidebar */
        JPanel buttonsSidebar = new JPanel(new GridLayout(1, 2));
        sidebar.add(buttonsSidebar, BorderLayout.NORTH);
        
        /* Adicionar Button Sidebar */
        icon = new ImageIcon(Main.getInstance().getAssets("add.png"));
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
        JScrollPane scrollSidebar = new JScrollPane(lista, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollSidebar.setBorder(null);
        sidebar.add(scrollSidebar, BorderLayout.CENTER);
        
        
        
        
        
        /* Panel Content */
        JPanel content = new JPanel(new BorderLayout(5, 5));
        add(content, BorderLayout.CENTER);
        
        /* Titulo Content */
        titulo = new JTextField();
        titulo.setFont(font);
        titulo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 0, 0, 5), border));
        titulo.setBackground(BACKGROUND_COLOR);
        content.add(titulo, BorderLayout.NORTH);
        
        /* Texto Content */
        texto = new JTextArea();
        texto.setBorder(border);
        texto.setFont(font);
        texto.setLineWrap(true);
        texto.setWrapStyleWord(true);
        texto.setBackground(BACKGROUND_COLOR);
        
        /* Scroll Content */
        JScrollPane scrollContent = new JScrollPane(texto, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollContent.setBorder(null);
        content.add(scrollContent, BorderLayout.CENTER);
        
        
    }
    
    
    
}
