package br.com.hrdev.docshost.server;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author henriqueschmidt
 */
public class Server implements Runnable {

    private Thread serverThread;
    private ServerSocket server;
    
    
    public Server(String ip, int port){
        try {
            server = new ServerSocket(port);
            serverThread = new Thread(this);
            serverThread.start();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                Socket socket = server.accept();
                Connection connection = new Connection(socket);
                connection.getThread().start();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}