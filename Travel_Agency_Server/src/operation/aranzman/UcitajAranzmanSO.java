/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.aranzman;

import domain.AbstractDomainObject;
import domain.Aranzman;
import operation.AbstractGenericOperation;

/**
 *
 * @author natalija
 */
public class UcitajAranzmanSO extends AbstractGenericOperation{

   private AbstractDomainObject entity;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    if (param == null || !(param instanceof Aranzman)) {
            throw new Exception("Nevalidan parametar");
        }    
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Aranzman aranzman = (Aranzman) param;
        entity = repository.getByID(aranzman);
    }

    public AbstractDomainObject getEntity() {
        return entity;
    }

   
    
}
