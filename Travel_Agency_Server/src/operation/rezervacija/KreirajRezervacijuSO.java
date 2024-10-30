/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.rezervacija;

import domain.RasporedSoba;
import domain.Rezervacija;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author natalija
 */
public class KreirajRezervacijuSO extends AbstractGenericOperation{

    @Override
    public void preconditions(Object param) throws Exception {
       if(param == null || !(param instanceof Rezervacija)){
            throw new Exception("Nisu dobri parametri");
        }
    }

    @Override
    public void executeOperation(Object param) throws Exception {
        Rezervacija rezervacija = (Rezervacija) param;
        repository.add(rezervacija);
        
        List<RasporedSoba> rasporediSoba = rezervacija.getRasporediSoba();
        
        for(RasporedSoba r : rasporediSoba) {
            repository.add(r);
        }
    }
    
}