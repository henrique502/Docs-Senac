package br.com.hrdev.docshost.server;

import br.com.hrdev.docshost.api.Api;
import br.com.hrdev.shared.docs.Mensagem;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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

    @Override
    public void run() {
        Api api = new Api();
        while (true){
            try {
                Mensagem msg = stream.get();
                switch (msg.getComando()) {
                    case "quit":
                        quit();
                        return;
                    case "login":
                        api.login(stream, msg.getValue());
                        break;
                    default:
                        api.notfound(stream);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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

        private final ObjectInputStream ro;
        private final ObjectOutputStream wo;

        private Stream() throws IOException {
            this.wo = new ObjectOutputStream(cliente.getOutputStream());
            this.ro = new ObjectInputStream(cliente.getInputStream());
        }

        public void send(Mensagem msg) throws IOException {
            this.wo.writeObject(msg);
        }

        public Mensagem get() throws IOException, ClassNotFoundException {
            Mensagem msg = (Mensagem) ro.readObject();
            return msg;
        }

        public void log(String comando) {
            System.out.println(getIp() + ": " + comando);
        }
    }
}
