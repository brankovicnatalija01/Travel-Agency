/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.rasporedSoba;

import domain.RasporedSoba;
import domain.Rezervacija;
import operation.AbstractGenericOperation;

/**
 *
 * @author natalija
 */
public class UcitajMaxIDRasporedSoba extends AbstractGenericOperation {
     private int id;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof RasporedSoba)) {
            throw new Exception("Nevalidan parametar");
        }   
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        RasporedSoba rasporedSoba = (RasporedSoba) param;
        id = repository.returnMaxIndex(rasporedSoba);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
