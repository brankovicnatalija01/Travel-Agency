/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.destinacija;

import domain.AbstractDomainObject;
import domain.Destinacija;
import operation.AbstractGenericOperation;

/**
 *
 * @author natalija
 */
public class UcitajDestinaciju extends AbstractGenericOperation{

   private AbstractDomainObject entity;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    if (param == null || !(param instanceof Destinacija)) {
            throw new Exception("Nevalidan parametar");
        }    
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Destinacija destinacija = (Destinacija) param;
        entity = repository.getByID(destinacija);
    }

    public AbstractDomainObject getEntity() {
        return entity;
    }

}
