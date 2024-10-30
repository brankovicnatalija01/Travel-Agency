/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.destinacija;

import domain.Destinacija;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author natalija
 */
public class UcitajDestinacije extends AbstractGenericOperation{

    List<Destinacija> destinacije;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    //
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        destinacije = repository.getAll(new Destinacija());
    }

    public List<Destinacija> getDestinacije() {
        return destinacije;
    }

    public void setDestinacije(List<Destinacija> destinacije) {
        this.destinacije = destinacije;
    }
    
    

}
