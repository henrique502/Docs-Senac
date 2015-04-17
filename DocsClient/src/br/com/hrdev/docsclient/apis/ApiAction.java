package br.com.hrdev.docsclient.apis;

/**
 *
 * @author henriqueschmidt
 */
public abstract class ApiAction {
    
    public abstract void execute(Api.Stream stream);
}
