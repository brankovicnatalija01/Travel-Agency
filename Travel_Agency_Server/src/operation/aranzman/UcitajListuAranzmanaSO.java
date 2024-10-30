/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.aranzman;

import domain.Aranzman;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author natalija
 */
public class UcitajListuAranzmanaSO extends AbstractGenericOperation{

    List<Aranzman> aranzmani;
    
    @Override
    protected void preconditions(Object param) throws Exception {
//      if(param == null || !(param instanceof Aranzman)){
//           throw new Exception("Nisu dobri parametri");
//        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        aranzmani = repository.getAll(new Aranzman());
    }

    public List<Aranzman> getAranzmani() {
        return aranzmani;
    }

    public void setAranzmani(List<Aranzman> aranzmani) {
        this.aranzmani = aranzmani;
    }

    
}
