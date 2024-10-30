/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.zaposleni;

import domain.AbstractDomainObject;
import domain.Zaposleni;
import operation.AbstractGenericOperation;

/**
 *
 * @author natalija
 */
public class UcitajZaposlenog extends AbstractGenericOperation{

   private AbstractDomainObject entity;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    if (param == null || !(param instanceof Zaposleni)) {
            throw new Exception("Nevalidan parametar");
        }    
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Zaposleni zaposleni = (Zaposleni) param;
        entity = repository.getByID(zaposleni);
    }

    public AbstractDomainObject getEntity() {
        return entity;
    }

}

