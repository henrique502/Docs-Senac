package br.com.hrdev.docsclient.apis;

import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author henriqueschmidt
 */
public abstract class ApiAction {
    
    public abstract void execute(InputStream in, OutputStream out);
}
