package br.com.hrdev.shared.docs;

import java.io.Serializable;

/**
 *
 * @author henriqueschmidt
 */
public abstract class Shared implements Serializable {
    
    private Long id = null;
    
    public Long getId(){
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Shared id: " + this.id;
    }
}
