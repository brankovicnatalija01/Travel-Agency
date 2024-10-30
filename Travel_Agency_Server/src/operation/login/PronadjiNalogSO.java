/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.login;

import domain.Zaposleni;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author natalija
 */
public class PronadjiNalogSO extends AbstractGenericOperation {
    
    Zaposleni zaposleni;
    
    @Override
    public void preconditions(Object param) throws Exception {
        if(param == null || !(param instanceof Zaposleni)){
            throw new Exception("Nisu dobri parametri");
        }
    }

    @Override
    public void executeOperation(Object param) throws Exception {
            Zaposleni z = (Zaposleni) param;
            zaposleni = (Zaposleni) repository.login(z, z.getUsername(), z.getPassword());
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }
    
    
   
}
