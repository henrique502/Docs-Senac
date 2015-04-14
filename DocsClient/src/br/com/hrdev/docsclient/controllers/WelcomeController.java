package br.com.hrdev.docsclient.controllers;

import br.com.hrdev.docsclient.apis.Api;
import br.com.hrdev.docsclient.views.WelcomeView;
import br.com.hrdev.docsclient.views.Window;

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
            public void run() {
                System.out.println("Execute Api Init");
                Api.getInstance();
                Window.getInstance().setStatusText("Conectado");
                Window.getInstance().changeView(Window.ViewID.LOGIN);
            }
        });
        thread.start();
    }
}
