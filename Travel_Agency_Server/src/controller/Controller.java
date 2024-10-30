/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.AbstractDomainObject;
import domain.Aranzman;
import domain.Destinacija;
import domain.Korisnik;
import domain.RasporedSoba;
import domain.Rezervacija;
import domain.TipAranzmana;
import domain.Zaposleni;
import java.util.ArrayList;
import java.util.List;
import operation.AbstractGenericOperation;
import operation.aranzman.IzmeniAranzmanSO;
import operation.aranzman.KreirajAranzmanSO;
import operation.aranzman.ObrisiAranzmanSO;
import operation.aranzman.PronadjiAranzmaneSO;
import operation.aranzman.UcitajAranzmanSO;
import operation.aranzman.UcitajListuAranzmanaSO;
import operation.rasporedSoba.UcitajMaxIDRasporedSoba;
import operation.aranzman.UcitajMaxIDRezervacije;
import operation.destinacija.UcitajDestinacije;
import operation.destinacija.UcitajDestinaciju;
import operation.korisnik.IzmeniKorisnikaSO;
import operation.korisnik.KreirajKorisnikaSO;
import operation.korisnik.ObrisiKorisnikaSO;
import operation.korisnik.PronadjiKorisnikeSO;
import operation.korisnik.UcitajKorisnikaSO;
import operation.korisnik.UcitajListuKorisnikaSO;
import operation.login.PronadjiNalogSO;
import operation.rasporedSoba.PronadjIRasporedeSobaPoIDKorisnika;
import operation.rasporedSoba.PronadjiRasporedeSobaPoIDRezervacije;
import operation.rezervacija.IzmeniRezervacijuSO;
import operation.rezervacija.KreirajRezervacijuSO;
import operation.rezervacija.PronadjiRezervacijeSO;
import operation.rezervacija.UcitajListuRezervacijaSO;
import operation.rezervacija.VratiRezervacije;
import operation.zaposleni.UcitajZaposlenog;
import repository.Repository;
import repository.db.impl.RepositoryDBGeneric;
import server.Server;

/**
 *
 * @author natalija
 */
public class Controller { 
    private static Controller instance;
    private final Repository repositoryGeneric;
    Server server;
    public List<AbstractDomainObject> prijavljeniZaposleni;

    public Controller() {
        this.repositoryGeneric = new RepositoryDBGeneric();
        prijavljeniZaposleni = new ArrayList<>();

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public boolean startujServer() {
        if (server == null || !server.isAlive()) {
            server = new Server();
            server.start();
            System.out.println("Server je pokrenut");
            return true;
        } else {
            return false;
        }
    }

    public void stopServer() throws Exception {
        System.out.println("Zaustavljen server");
        server.kraj();
    }

    public List<AbstractDomainObject> getPrijavljeniZaposleni() {
        return prijavljeniZaposleni;
    }

    public void setPrijavljeniZaposleni(List<AbstractDomainObject> prijavljeniZaposleni) {
        this.prijavljeniZaposleni = prijavljeniZaposleni;
    }

    public AbstractDomainObject login(String username, String password) throws Exception {
        Zaposleni zaposleni = new Zaposleni();
        zaposleni.setUsername(username);
        zaposleni.setPassword(password);

        PronadjiNalogSO operation = new PronadjiNalogSO();
        
        operation.execute(zaposleni);
        
        return ((PronadjiNalogSO) operation).getZaposleni();
    }

    public void dodajKorisnika(Korisnik korisnik) throws Exception {
       AbstractGenericOperation operation = new KreirajKorisnikaSO();
       operation.execute(korisnik);
    }

    public void izmeniKorisnika(Korisnik korisnik2) throws Exception {
       AbstractGenericOperation operation = new IzmeniKorisnikaSO();
       operation.execute(korisnik2);
    }

    public List<Korisnik> ucitajKorisnike() throws Exception {
        AbstractGenericOperation operation = new UcitajListuKorisnikaSO();
        operation.execute(null);
        return ((UcitajListuKorisnikaSO) operation).getKorisnici();  
    }

    public void obrisiKorisnika(Korisnik korisnik3) throws Exception {
      AbstractGenericOperation operation = new ObrisiKorisnikaSO();
      operation.execute(korisnik3);
    }

    public List<Destinacija> ucitajDestinacije() throws Exception {
        AbstractGenericOperation operation = new UcitajDestinacije();
        operation.execute(null);
        return ((UcitajDestinacije) operation).getDestinacije();
    
    }
    
    public List<Aranzman> vratiSveAranzmane() throws Exception {
        AbstractGenericOperation operation = new UcitajListuAranzmanaSO();
        operation.execute(new Aranzman());
        List<Aranzman> aranzmani = ((UcitajListuAranzmanaSO) operation).getAranzmani();
        
        for (AbstractDomainObject entity : aranzmani) {
            Aranzman a = (Aranzman) entity;
            a.setDestinacija((Destinacija) vratiDestinaciju(a.getDestinacija().getDestinacijaID()));
           
        }
        return aranzmani;
    }
    
    public AbstractDomainObject vratiDestinaciju(int id) throws Exception {
        Destinacija destinacija = new Destinacija();
        destinacija.setDestinacijaID(id);
        AbstractGenericOperation operation = new UcitajDestinaciju();
        operation.execute(destinacija);
        return ((UcitajDestinaciju) operation).getEntity();
    }

    public void dodajAranzman(Aranzman aranzman) throws Exception {
        AbstractGenericOperation operation = new KreirajAranzmanSO();
        operation.execute(aranzman);
    }

    public void izmeniAranzman(Aranzman aranzman2) throws Exception {
        AbstractGenericOperation operation = new IzmeniAranzmanSO();
        operation.execute(aranzman2);
    }

    public List<Aranzman> ucitajAranzmane() throws Exception {
        AbstractGenericOperation operation = new UcitajListuAranzmanaSO();
        operation.execute(null);
        return ((UcitajListuAranzmanaSO) operation).getAranzmani();
    }

    public void obrisiAranzman(Aranzman aranzman3) throws Exception {
        AbstractGenericOperation operation = new ObrisiAranzmanSO();
        operation.execute(aranzman3);
    }

    public void dodajRezervaciju(Rezervacija rezervacija) throws Exception {
       AbstractGenericOperation operation = new KreirajRezervacijuSO();
       operation.execute(rezervacija);
    }

    public List<Rezervacija> vratiSveRezervacije() throws Exception {
       AbstractGenericOperation operation = new UcitajListuRezervacijaSO();
       operation.execute(new Rezervacija());
       List<Rezervacija> rezervacije = ((UcitajListuRezervacijaSO) operation).getRezervacije();
        
       for (AbstractDomainObject entity : rezervacije) {
            Rezervacija r = (Rezervacija) entity;
            r.setAranzman((Aranzman) vratiAranzman(r.getAranzman().getAranzmanID()));
       }
       
       
       for (AbstractDomainObject entity : rezervacije) {
            Rezervacija r = (Rezervacija) entity;
            r.setZaposleni((Zaposleni) vratiZaposlenog(r.getZaposleni().getZaposleniID()));
       }
       
       return rezervacije;
    }
    
     public AbstractDomainObject vratiKorisnika(int id) throws Exception {
        Korisnik korisnik = new Korisnik();
        korisnik.setKorisnikID(id);
       
        AbstractGenericOperation operation = new UcitajKorisnikaSO();
        operation.execute(korisnik);
        return ((UcitajKorisnikaSO) operation).getEntity();
    }
     
     public AbstractDomainObject vratiAranzman(int id) throws Exception {
        Aranzman aranzman = new Aranzman();
        aranzman.setAranzmanID(id);
        
        AbstractGenericOperation operation = new UcitajAranzmanSO();
        operation.execute(aranzman);
        aranzman =  (Aranzman) ((UcitajAranzmanSO) operation).getEntity();
        
        aranzman.setDestinacija((Destinacija) vratiDestinaciju(aranzman.getDestinacija().getDestinacijaID()));
        
        return aranzman;
    }
     
     public AbstractDomainObject vratiZaposlenog(int id) throws Exception {
        Zaposleni zaposleni = new Zaposleni();
        zaposleni.setZaposleniID(id);
       
        AbstractGenericOperation operation = new UcitajZaposlenog();
        operation.execute(zaposleni);
        return ((UcitajZaposlenog) operation).getEntity();
    }

    public int vratiMaxIDRezervacije() throws Exception {
       AbstractGenericOperation operation = new UcitajMaxIDRezervacije();
       operation.execute(new Rezervacija());
       return ((UcitajMaxIDRezervacije) operation).getId();
    }

    public int vratiMaxIDRasporedSoba() throws Exception {
       AbstractGenericOperation operation = new UcitajMaxIDRasporedSoba();
       operation.execute(new RasporedSoba());
      return ((UcitajMaxIDRasporedSoba) operation).getId();
    }

    public List<AbstractDomainObject> pretragaKorisnika(String ime) throws Exception {
        Korisnik korisnik = new Korisnik();
        korisnik.setIme(ime);
        AbstractGenericOperation operation = new PronadjiKorisnikeSO();
        operation.execute(korisnik);

        List<AbstractDomainObject> korisnici = ((PronadjiKorisnikeSO) operation).getList();
 
        return korisnici;
    }

    public List<AbstractDomainObject> pretragaAranzmana(String tipAranzmana) throws Exception {
        Aranzman aranzman = new Aranzman();
        aranzman.setFilter(tipAranzmana);
        AbstractGenericOperation operation = new PronadjiAranzmaneSO();
        operation.execute(aranzman);

        List<AbstractDomainObject> aranzmani = ((PronadjiAranzmaneSO) operation).getList();
        
        for(AbstractDomainObject object : aranzmani) {
            Aranzman a =  (Aranzman) object;
            a.setDestinacija((Destinacija) vratiDestinaciju(a.getDestinacija().getDestinacijaID()));
        }
 
        return aranzmani;
      
    }

    public List<AbstractDomainObject> pretragaRezervacija(String sifraRezervacije) throws Exception {
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setSifraRezervacije(sifraRezervacije);
        AbstractGenericOperation operation = new PronadjiRezervacijeSO();
        operation.execute(rezervacija);

        List<AbstractDomainObject> rezervacije = ((PronadjiRezervacijeSO) operation).getList();
        
        for(AbstractDomainObject object : rezervacije) {
            Rezervacija r =  (Rezervacija) object;
            r.setZaposleni((Zaposleni) vratiZaposlenog(r.getZaposleni().getZaposleniID()));
            r.setAranzman((Aranzman) vratiAranzman(r.getAranzman().getAranzmanID()));
        }
 
        return rezervacije;
        
    }

    public List<AbstractDomainObject> ucitajRasporedeSobaPoRezervaciji(Rezervacija rezervacija) throws Exception {
        RasporedSoba rasporedSoba = new RasporedSoba();
        rasporedSoba.setRezervacija(rezervacija);
        
        AbstractGenericOperation operation = new PronadjiRasporedeSobaPoIDRezervacije();
        operation.execute(rasporedSoba);

        List<AbstractDomainObject> rasporediSoba = ((PronadjiRasporedeSobaPoIDRezervacije) operation).getList();
        
        for(AbstractDomainObject object : rasporediSoba) {
            RasporedSoba rs =  (RasporedSoba) object;
            rs.setKorisnik((Korisnik) vratiKorisnika(rs.getKorisnik().getKorisnikID()));
        }
 
        return rasporediSoba;
    }

    public void izmeniRezervaciju(Rezervacija rezervacija3) throws Exception {
        AbstractGenericOperation operation = new IzmeniRezervacijuSO();
        operation.execute(rezervacija3);
    }

  

    public List<Rezervacija> vratiRezervacije2() throws Exception {
        AbstractGenericOperation operation = new VratiRezervacije();
        operation.execute(new Rezervacija());
        List<Rezervacija> rezervacije = ((VratiRezervacije) operation).getRezervacije();
        
       for (AbstractDomainObject entity : rezervacije) {
            Rezervacija r = (Rezervacija) entity;
            r.setAranzman((Aranzman) vratiAranzman(r.getAranzman().getAranzmanID()));
       }
       
       
       for (AbstractDomainObject entity : rezervacije) {
            Rezervacija r = (Rezervacija) entity;
            r.setZaposleni((Zaposleni) vratiZaposlenog(r.getZaposleni().getZaposleniID()));
       }
       
       return rezervacije;
    }

    public List<Rezervacija> vratiRezervacije2(Korisnik korisnik5) throws Exception {
        AbstractGenericOperation operation = new VratiRezervacije();
        operation.execute(korisnik5);
        
        List<Rezervacija> rezervacije = ((VratiRezervacije) operation).getRezervacije();
        
       for (AbstractDomainObject entity : rezervacije) {
            Rezervacija r = (Rezervacija) entity;
            r.setAranzman((Aranzman) vratiAranzman(r.getAranzman().getAranzmanID()));
       }
       
       
       for (AbstractDomainObject entity : rezervacije) {
            Rezervacija r = (Rezervacija) entity;
            r.setZaposleni((Zaposleni) vratiZaposlenog(r.getZaposleni().getZaposleniID()));
       }
       
       return rezervacije;
    }

    public List<AbstractDomainObject> ucitajRasporedeSobaPoKorisniku(Korisnik korisnik6) throws Exception {
        RasporedSoba rasporedSoba = new RasporedSoba();
        rasporedSoba.setKorisnik(korisnik6);
   
        
        AbstractGenericOperation operation = new PronadjIRasporedeSobaPoIDKorisnika();
        operation.execute(rasporedSoba);

      List<AbstractDomainObject> rasporediSoba = ((PronadjIRasporedeSobaPoIDKorisnika) operation).getList();
        
        for(AbstractDomainObject object : rasporediSoba) {
            RasporedSoba rs =  (RasporedSoba) object;
            rs.setKorisnik((Korisnik) vratiKorisnika(rs.getKorisnik().getKorisnikID()));
        }
        
//        for(AbstractDomainObject object : rasporediSoba) {
//            RasporedSoba rs =  (RasporedSoba) object;
//        //    rs.setRezervacija((Rezervacija) vratiRezer(rs.getKorisnik().getKorisnikID()));
//        }
 
        return rasporediSoba;

    }
   
}
