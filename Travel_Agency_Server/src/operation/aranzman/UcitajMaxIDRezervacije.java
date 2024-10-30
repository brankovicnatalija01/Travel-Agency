/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.aranzman;

import domain.Rezervacija;
import operation.AbstractGenericOperation;

/**
 *
 * @author natalija
 */
public class UcitajMaxIDRezervacije extends AbstractGenericOperation {
    
    private int id;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Rezervacija)) {
            throw new Exception("Nevalidan parametar");
        }   
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Rezervacija rezervacija = (Rezervacija) param;
        id = repository.returnMaxIndex(rezervacija);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
