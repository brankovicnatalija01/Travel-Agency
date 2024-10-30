/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.rezervacija;

import domain.Rezervacija;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author natalija
 */
public class UcitajListuRezervacijaSO extends AbstractGenericOperation{

    List<Rezervacija> rezervacije;
    
    @Override
    protected void preconditions(Object param) throws Exception {
//      if(param == null || !(param instanceof Rezervacija)){
//           throw new Exception("Nisu dobri parametri");
//        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        rezervacije = repository.getAll(new Rezervacija());
    }

    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }

    public void setRezervacije(List<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
    }

  
    
}
