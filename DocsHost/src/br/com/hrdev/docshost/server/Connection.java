package br.com.hrdev.docshost.server;

import br.com.hrdev.docshost.api.Api;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 *
 * @author henriqueschmidt
 */
public class Connection implements Runnable {
    
    private final Thread thread;
    private final Socket cliente;
    private final String ip;
    private final Stream stream;
    
    public Connection(Socket cliente) throws IOException {
        this.cliente = cliente;
        this.thread = new Thread(this);
        this.ip = cliente.getInetAddress().getHostAddress();
        this.stream = new Stream();
    }

    public Thread getThread() {
        return thread;
    }

    public Socket getCliente() {
        return cliente;
    }
    
    /**
     * new BufferedReader(new InputStreamReader(in));
     * new PrintStream(out);
     * new ObjectOutputStream(out);
     * new ObjectInputStream(in);
     */
    @Override
    public void run() {
        try {
            Scanner reader = new Scanner(cliente.getInputStream());
            Api api = new Api();
            while (reader.hasNextLine()){
                
                String comando = reader.nextLine().trim();
                stream.log(comando);
                switch(comando){
                    case "quit": quit(); break;
                    case "login": api.login(stream); break;
                    default: api.notfound(stream); break;
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void quit() throws IOException {
        cliente.close();
        thread.interrupt();
    }

    public String getIp() {
        return this.ip;
    }
    
    public class Stream {

        /**
         * readerObject - ObjectInputStream
         */
        public final ObjectInputStream ro;

        /**
         * writerObject - ObjectOutputStream
         */
        public final ObjectOutputStream wo;

        /**
         * reader - BufferedReader
         */
        public final BufferedReader r;

        /**
         * writer - PrintStream
         */
        public final PrintStream w;

        private Stream() throws IOException {
            this.r = new BufferedReader(new InputStreamReader(cliente.getInputStream(), StandardCharsets.UTF_8));
            this.ro = new ObjectInputStream(cliente.getInputStream());
            
            this.w = new PrintStream(cliente.getOutputStream());
            this.wo = new ObjectOutputStream(cliente.getOutputStream());
        }

        public void log(String comando) {
            System.out.println(getIp() + ": " + comando);
        }
    }
}
