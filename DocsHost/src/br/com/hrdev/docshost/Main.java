package br.com.hrdev.docshost;

import br.com.hrdev.shared.docs.api.Api;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author henriqueschmidt
 */
public class Main {
    
    public final static String BASE_DIR = System.getProperty("user.dir");
    public final static int SERVER_PORT = 1099;
    
    protected Main(){}
    
    public static void main(String[] args) {
        try {
            Api api = new ServerApi();
            LocateRegistry.createRegistry(SERVER_PORT);
            
            try {
                 Naming.rebind("Api", api);
            } catch(MalformedURLException  ex){
                ex.printStackTrace();
            }
        } catch(RemoteException ex){
            ex.printStackTrace();
        }
    }
}
