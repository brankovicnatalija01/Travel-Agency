/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.aranzman;

import domain.Aranzman;
import domain.Korisnik;
import operation.AbstractGenericOperation;

/**
 *
 * @author natalija
 */
public class ObrisiAranzmanSO extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null || !(param instanceof Aranzman)){
            throw new Exception("Sistem nije mogao da obrise aranžman");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.delete((Aranzman)param);
    }
    
}
