package br.com.hrdev.docsclient.apis;

/**
 *
 * @author henriqueschmidt
 */
public enum ApiTalk {
    
    QUIT("quit"), LOGIN("login"), OK("ok");
    
    private final String command;

    private ApiTalk(String command){
        this.command = command;
    }

    @Override
    public String toString() {
        return this.command;
    }
}
