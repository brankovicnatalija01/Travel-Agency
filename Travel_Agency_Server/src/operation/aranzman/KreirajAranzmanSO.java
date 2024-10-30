/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.aranzman;

import domain.Aranzman;
import operation.AbstractGenericOperation;

/**
 *
 * @author natalija
 */
public class KreirajAranzmanSO extends AbstractGenericOperation{

    @Override
    public void preconditions(Object param) throws Exception {
       if(param == null || !(param instanceof Aranzman)){
            throw new Exception("Nisu dobri parametri");
        }
    }

    @Override
    public void executeOperation(Object param) throws Exception {
        repository.add((Aranzman)param);
    }
    
}
