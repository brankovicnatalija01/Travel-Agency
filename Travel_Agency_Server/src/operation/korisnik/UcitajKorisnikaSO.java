/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.korisnik;

import domain.AbstractDomainObject;
import domain.Aranzman;
import domain.Korisnik;
import operation.AbstractGenericOperation;

/**
 *
 * @author natalija
 */
public class UcitajKorisnikaSO extends AbstractGenericOperation{

   private AbstractDomainObject entity;
    
    @Override
    protected void preconditions(Object param) throws Exception {
//    if (param == null || !(param instanceof Korisnik)) {
//            throw new Exception("Nevalidan parametar");
//        }    
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Korisnik korisnik = (Korisnik) param;
        entity = repository.getByID(korisnik);
    }

    public AbstractDomainObject getEntity() {
        return entity;
    }

   
    
}
