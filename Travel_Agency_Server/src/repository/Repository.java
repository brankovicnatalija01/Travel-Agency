/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import domain.AbstractDomainObject;
import domain.Rezervacija;
import java.util.List;

/**
 *
 * @author natalija
 */
public interface Repository <T> {
    List<T> getAll(T param) throws Exception;
    void add(T param) throws Exception;
    void edit(T param) throws Exception;
    void delete(T param) throws Exception;
    public AbstractDomainObject getByID(T param) throws Exception;
    public int returnMaxIndex(AbstractDomainObject object) throws Exception;
    public List<AbstractDomainObject> filter(AbstractDomainObject object) throws Exception;
    public AbstractDomainObject login(AbstractDomainObject object, String username, String password) throws Exception;
    public List<AbstractDomainObject> getByRezervacijaID(AbstractDomainObject object, int rezervacijaID) throws Exception;

    public List<AbstractDomainObject> getByKorisnikID(AbstractDomainObject param, int korisnikID);
    
}
