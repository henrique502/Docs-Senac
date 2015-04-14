package br.com.hrdev.docsclient.apis;

import br.com.hrdev.docsclient.Main;
import br.com.hrdev.docsclient.views.Window;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author henriqueschmidt
 */
public class Api {
    
    private static Api instance = null;
    private Socket socket = null;
    private OutputStream out;
    private InputStream in;
    
    protected Api(){}
    
    public static Api getInstance(){
        if(instance == null){
            instance = new Api();
            instance.run();
        }
        
        return instance;
    }
    
    /**
     * new BufferedReader(new InputStreamReader(in));
     * new PrintStream(out);
     * new ObjectOutputStream(out);
     * new ObjectInputStream(in);
     */
    private void run() {
        try {
            socket = new Socket(Main.SERVER_IP, Main.SERVER_PORT);
            in = socket.getInputStream();
            out = socket.getOutputStream();
            System.out.println("connected: " + Main.SERVER_IP + ":" + Main.SERVER_PORT);
        } catch(ConnectException e){
            JOptionPane.showMessageDialog(Window.getInstance(), e.getMessage(), "Erro ao conenctar", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void close(){
        try {
            socket.close();
        } catch(Exception e){}
    }
    
    public void execute(final ApiAction action) throws Exception {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                action.execute(in, out);
            }
        });
        thread.start();
    }
    
    public static boolean apiIsInit(){
        return instance != null;
    }
}