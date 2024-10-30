/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.rezervacija;

import domain.AbstractDomainObject;
import domain.RasporedSoba;
import domain.Rezervacija;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author natalija
 */
public class IzmeniRezervacijuSO extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
       if(param == null || !(param instanceof Rezervacija)){
            throw new Exception("Nisu dobri parametri");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Rezervacija rezervacija = (Rezervacija) param;
        repository.edit(rezervacija);
        
        List<RasporedSoba> noviRasporediSoba = rezervacija.getRasporediSoba();
 
        List<RasporedSoba> stariRasporediSoba = repository.getByRezervacijaID(noviRasporediSoba.get(0), rezervacija.getRezervacijaID());
        
        for(RasporedSoba l : stariRasporediSoba) {
            repository.delete(l);
        }
        
        for(RasporedSoba r : noviRasporediSoba) {
            repository.add(r);
        }
    }
    
    
}
