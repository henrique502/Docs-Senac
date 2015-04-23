package br.com.hrdev.docshost.server;

import br.com.hrdev.docshost.api.Api;
import br.com.hrdev.shared.docs.Mensagem;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author henriqueschmidt
 */
public class Connection implements Runnable {

    private final Socket cliente;
    private final String ip;
    private final Stream stream;

    public Connection(Socket cliente) throws IOException {
        this.cliente = cliente;
        this.ip = cliente.getInetAddress().getHostAddress();
        this.stream = new Stream();
    }
    
    public Socket getCliente() {
        return cliente;
    }

    @Override
    public void run() {
        Api api = new Api();
        try {
            Mensagem msg = stream.get();
            switch (msg.getComando()) {
                case "login":
                    api.login(stream, msg.getValue());
                    break;
                case "cadastro":
                    api.cadastro(stream, msg.getValue());
                    break;
                default: break;
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        } finally {
            quit();
        }

    }

    private void quit() {
        try {
            cliente.close();
        } catch (IOException e) { }
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

        public Mensagem get() throws IOException, ClassNotFoundException, EOFException {
            Mensagem msg = (Mensagem) ro.readObject();
            return msg;
        }

        public void log(String comando) {
            System.out.println(getIp() + ": " + comando);
        }
    }
}
