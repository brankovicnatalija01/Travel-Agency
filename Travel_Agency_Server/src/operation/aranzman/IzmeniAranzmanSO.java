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
public class IzmeniAranzmanSO extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Aranzman)) {
            throw new Exception("Nevalidni parametri");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.edit((Aranzman) param);
    }
    
}
