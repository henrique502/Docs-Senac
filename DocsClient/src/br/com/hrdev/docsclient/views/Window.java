package br.com.hrdev.docsclient.views;

import br.com.hrdev.docsclient.apis.Api;
import br.com.hrdev.shared.docs.Usuario;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 *
 * @author henriqueschmidt
 */
public class Window extends JFrame {
    
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    public static final Color BACKGROUND_COLOR = new Color(238, 238, 238);
    private static Window instance = null;
    private CardLayout layout;
    private JMenuBar menuBar;
    private JMenu menuStatus;
    private ArrayList<View> views;
    private ViewID currentView;
    private Usuario usuario = null;

    protected Window() {}
    
    public static Window getInstance(){
        if(instance == null){
            instance = new Window();
            
            instance.setAtributos();
            instance.setViews();
        }
        
        return instance;
    }
    
    private void setAtributos() {
        Dimension windowSize = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(windowSize);
        setMinimumSize(windowSize);
        
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        setMaximumSize(new Dimension(gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight()));
        
        layout = new CardLayout();
        menuBar = new JMenuBar();
        menuBar.add(Box.createHorizontalGlue());
        menuStatus = new JMenu("Bem Vindo");
        menuStatus.setEnabled(false);
        
        menuBar.add(menuStatus);
        
        setJMenuBar(menuBar);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBackground(BACKGROUND_COLOR);
        
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Api.getInstance().close();
                dispose();
                System.exit(0);
            }
        });
        setContentPane(new JPanel(new BorderLayout()));
        setLayout(layout);
    }
    
    public void changeView(Window.ViewID view){
        layout.show(getContentPane(), view.toString());
        currentView = view;
        
        getCurrentView().actived();
    }
    

    private void setViews() {
        views = new ArrayList<>();
        JPanel panel = (JPanel) getContentPane();
        
        /* Welcome */
        View view = new WelcomeView();
        views.add(ViewID.WELCOME.getIndex(), view);
        panel.add(view, ViewID.WELCOME.toString());
        
        /* Login */
        view = new LoginView();
        views.add(ViewID.LOGIN.getIndex(), view);
        panel.add(view, ViewID.LOGIN.toString());
        
        /* Cadastro */
        view = new CadastroView();
        views.add(ViewID.CADASTRO.getIndex(), view);
        panel.add(view, ViewID.CADASTRO.toString());
        
        /* Dashboard */
        view = new DashboardView();
        views.add(ViewID.DASHBOARD.getIndex(), view);
        panel.add(view, ViewID.DASHBOARD.toString());
        
        changeView(ViewID.WELCOME);
    }
    
    public View getCurrentView(){
        return views.get(currentView.getIndex());
    }
    
    public void setStatusText(String text){
        menuStatus.setText(text);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public enum ViewID {
        WELCOME("Welcome", 0),
        LOGIN("Login", 1),
        CADASTRO("Cadastro", 2),
        DASHBOARD("Dashboard", 3);
        
        private final String name;
        private final int index;
        
        private ViewID(String name, int index){
            this.name = name;
            this.index = index;
        }

        public int getIndex(){
            return this.index;
        }
        
        @Override
        public String toString() {
            return this.name;
        }
    }
}
