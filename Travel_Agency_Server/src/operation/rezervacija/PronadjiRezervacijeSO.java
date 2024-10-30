/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.rezervacija;

import domain.AbstractDomainObject;
import domain.Rezervacija;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author natalija
 */
public class PronadjiRezervacijeSO extends AbstractGenericOperation{
     private List<AbstractDomainObject> list;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Rezervacija)) {
            throw new Exception("Nevalidan parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list = repository.filter((Rezervacija) param);
    }

    public List<AbstractDomainObject> getList() {
        return list;
    }
}
