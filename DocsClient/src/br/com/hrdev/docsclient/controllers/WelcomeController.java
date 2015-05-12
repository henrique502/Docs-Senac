package br.com.hrdev.docsclient.controllers;

import br.com.hrdev.docsclient.Main;
import br.com.hrdev.docsclient.views.WelcomeView;
import br.com.hrdev.docsclient.views.Window;
import br.com.hrdev.shared.docs.api.Api;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author henriqueschmidt
 */
public class WelcomeController extends Controller {
    
    private final WelcomeView view;

    public WelcomeController(WelcomeView view) {
        this.view = view;
    }

    public void init() {}
    
    public void initApi(){
        Window.getInstance().setStatusText("Conectado...");
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run(){
                try {
                    api = (Api) Naming.lookup("rmi://" + Main.SERVER_IP + ":" + Main.SERVER_PORT + "/Api");
                    Window.getInstance().setStatusText("Conectado");
                    Window.getInstance().changeView(Window.ViewID.LOGIN);
                } catch (NotBoundException | MalformedURLException | RemoteException ex) {
                    ex.printStackTrace();
                    showErro(ex.getMessage());
                    System.exit(0);
                }
            }
        });
        thread.start();
    }
}
