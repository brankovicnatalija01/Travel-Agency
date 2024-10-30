/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.korisnik;

import domain.Korisnik;
import operation.AbstractGenericOperation;

/**
 *
 * @author natalija
 */
public class KreirajKorisnikaSO extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
       if(param == null || !(param instanceof Korisnik)){
            throw new Exception("Nisu dobri parametri");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Korisnik)param);
    }
    
}
