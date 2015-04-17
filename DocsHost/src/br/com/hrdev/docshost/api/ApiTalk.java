package br.com.hrdev.docshost.api;

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
    
    public String getValue() {
        return command;
    }
}
