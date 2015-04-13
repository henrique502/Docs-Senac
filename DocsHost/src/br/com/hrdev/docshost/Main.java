package br.com.hrdev.docshost;

import java.awt.EventQueue;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import javax.swing.UIManager;

/**
 *
 * @author henriqueschmidt
 */
public class Main implements Runnable {
    
    private static Main instance = null;
    public static String BASE_DIR = null;
    public static Integer SERVER_PORT = null;
    public static String LOCAL_IP = null;
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            Main main = Main.getInstance();
            EventQueue.invokeLater(main);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static String getLocalIp() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            return ip.getHostAddress();
        } catch (UnknownHostException ex) {
            return "0.0.0.0";
        }
    }
    
    public static Main getInstance(){
        if(instance == null){
            instance = new Main();
        }
        
        return instance;
    }
    
    protected Main(){
        BASE_DIR = System.getProperty("user.dir");
        SERVER_PORT = 7896;
        LOCAL_IP = getLocalIp();
    }
    
    public URL getAssets(String path){
        URL input = null;
        
        try {
            input = getClass().getResource("/resources/" + path);
        } catch(Exception e){
            System.err.println("Asset nao encontrado (/resources/" + path + ")");
        }
        
        return input;
    }

    @Override
    public void run() {
        
    }
}
