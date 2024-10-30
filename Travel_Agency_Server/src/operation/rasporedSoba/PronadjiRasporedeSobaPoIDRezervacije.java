/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.rasporedSoba;

import domain.AbstractDomainObject;
import domain.RasporedSoba;
import domain.Zaposleni;
import java.util.ArrayList;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author natalija
 */
public class PronadjiRasporedeSobaPoIDRezervacije extends AbstractGenericOperation{

    List<AbstractDomainObject> list = new ArrayList<>();
    
    @Override
    protected void preconditions(Object param) throws Exception {
       
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        RasporedSoba rs = (RasporedSoba) param;
        list = repository.getByRezervacijaID(rs, rs.getRezervacija().getRezervacijaID());
    }

   public List<AbstractDomainObject> getList() {
        return list;
    }
    
}
