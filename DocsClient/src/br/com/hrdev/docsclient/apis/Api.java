package br.com.hrdev.docsclient.apis;

import br.com.hrdev.docsclient.Main;
import br.com.hrdev.docsclient.views.Window;
import br.com.hrdev.shared.docs.Mensagem;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private Stream stream;
    
    protected Api(){}
    
    public static Api getInstance(){
        if(instance == null){
            instance = new Api();
            instance.run();
        }
        
        return instance;
    }
    
    private void run() {
        try {
            socket = new Socket(Main.SERVER_IP, Main.SERVER_PORT);
            stream = new Stream();
            
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
                action.execute(stream);
            }
        });
        thread.start();
    }
    
    public static boolean apiIsInit(){
        return instance != null;
    }
    
    public class Stream {

        private final ObjectInputStream ro;
        private final ObjectOutputStream wo;

        private Stream() throws IOException {
            this.ro = new ObjectInputStream(socket.getInputStream());
            this.wo = new ObjectOutputStream(socket.getOutputStream());
        }
        
        public void send(Mensagem msg) throws IOException {
            this.wo.writeObject(msg);
        }
        
        public Mensagem get() throws IOException, ClassNotFoundException{
            Mensagem msg = (Mensagem) ro.readObject();
            return msg;
        }
    }
}