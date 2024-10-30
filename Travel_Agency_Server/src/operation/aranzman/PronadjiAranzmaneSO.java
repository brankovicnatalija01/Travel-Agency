/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.aranzman;

import domain.AbstractDomainObject;
import domain.Aranzman;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author natalija
 */
public class PronadjiAranzmaneSO extends AbstractGenericOperation{
     private List<AbstractDomainObject> list;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Aranzman)) {
            throw new Exception("Nevalidan parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list = repository.filter((Aranzman)param);
    }

    public List<AbstractDomainObject> getList() {
        return list;
    }
    
}
