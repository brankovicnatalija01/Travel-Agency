/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.thread;

import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import controller.Controller;
import domain.AbstractDomainObject;
import domain.Aranzman;
import domain.Destinacija;
import domain.Korisnik;
import domain.Rezervacija;
import domain.Zaposleni;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import operation.AbstractGenericOperation;
import static operations.Operation.AZURIRAJ_KORISNIKA;
import static operations.Operation.DODAJ_ARANZMAN;
import static operations.Operation.DODAJ_KORISNIKA;
import static operations.Operation.UCITAJ_KORISNIKE;
import static operations.Operation.VRATI_MAX_ID_REZERVACIJE;
import server.Server;

/**
 *
 * @author natalija
 */
public class ProcessClientsRequests extends Thread {
    
    private Socket socket;
    private Sender sender;
    private Receiver receiver;
    private Server server;
    private boolean kraj = false;
    
    public ProcessClientsRequests(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }
    
    @Override
    public void run() {
        while (!kraj) {
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();
                
                try {
                    switch (request.getOperation()) {
                      case LOGIN:
                            Zaposleni zaposleni = (Zaposleni) request.getArgument();
                            AbstractDomainObject entity = Controller.getInstance().login(zaposleni.getUsername(), zaposleni.getPassword());
                          
                            if (entity == null) {
                                response.setMessage("Sistem ne može da pronađe nalog");
                                
                            } else {
                                if (!Controller.getInstance().getPrijavljeniZaposleni().contains(entity)) {
                                    Controller.getInstance().getPrijavljeniZaposleni().add(entity);
                                    response.setResult(entity);
                                } else {
                                    response.setMessage("Korisnik je već prijavljen");
                                }
                            }
                            break;
                            
                      case DODAJ_KORISNIKA:
                            Korisnik korisnik = (Korisnik) request.getArgument();
                            Controller.getInstance().dodajKorisnika(korisnik);
                            response.setResult(null);
                            break;      
                      
                      case AZURIRAJ_KORISNIKA:
                            Korisnik korisnik2 = (Korisnik) request.getArgument();
                            Controller.getInstance().izmeniKorisnika(korisnik2);
                            response.setResult(null);
                            break;     
                            
                      case UCITAJ_KORISNIKE:
                          List<Korisnik> korisnici = Controller.getInstance().ucitajKorisnike();
                          response.setResult(korisnici);
                          break;
                          
                      case OBRISI_KORISNIKA:
                          Korisnik korisnik3 = (Korisnik) request.getArgument();
                          Controller.getInstance().obrisiKorisnika(korisnik3);
                          break;
                       
                      case VRATI_KORISNIKE_PO_IMENU:
                           String ime = (String) request.getArgument();
                           List<AbstractDomainObject> korisniciPoImenu = Controller.getInstance().pretragaKorisnika(ime);
                           response.setResult(korisniciPoImenu);                            
                           break;
                          
                      case UCITAJ_DESTINACIJE:
                          List<Destinacija> destinacije = Controller.getInstance().ucitajDestinacije();
                          response.setResult(destinacije);
                          break;
                          
                     case DODAJ_ARANZMAN:
                            Aranzman aranzman = (Aranzman) request.getArgument();
                            Controller.getInstance().dodajAranzman(aranzman);
                            response.setResult(null);
                            break;      
                      
                      case AZURIRAJ_ARANZMANE:
                            Aranzman aranzman2 = (Aranzman) request.getArgument();
                            Controller.getInstance().izmeniAranzman(aranzman2);
                            response.setResult(null);
                            break;  
                            
                      case UCITAJ_ARANZMANE:
                          List<Aranzman> aranzmani = Controller.getInstance().vratiSveAranzmane();
                          response.setResult(aranzmani);
                          break;
                          
                      case OBRISI_ARANZMAN:
                          Aranzman aranzman3 = (Aranzman) request.getArgument();
                          Controller.getInstance().obrisiAranzman(aranzman3);
                          break;
                       
                      case VRATI_ARANZMANE_PO_TIPU:
                           String tipAranzmana = (String) request.getArgument();
                           List<AbstractDomainObject> aranzmaniPoTipu = Controller.getInstance().pretragaAranzmana(tipAranzmana);
                           response.setResult(aranzmaniPoTipu);                            
                           break;
                          
                      case DODAJ_REZERVACIJU:
                            Rezervacija rezervacija = (Rezervacija) request.getArgument();
                            Controller.getInstance().dodajRezervaciju(rezervacija);
                            response.setResult(null);
                            break; 
                            
                      case UCITAJ_REZERVACIJE:
                          List<Rezervacija> rezervacije = Controller.getInstance().vratiSveRezervacije();
                          response.setResult(rezervacije);
                          break;
                          
                      case VRATI_MAX_ID_REZERVACIJE:
                          int id = Controller.getInstance().vratiMaxIDRezervacije();
                          response.setResult(id);
                          break;
                      
                      case VRATI_REZERVACIJE_PO_SIFRI:
                           String sifraRezervacije = (String) request.getArgument();
                           List<AbstractDomainObject> rezervacijePoSifri = Controller.getInstance().pretragaRezervacija(sifraRezervacije);
                           response.setResult(rezervacijePoSifri);                            
                           break;
                          
                      case VRATI_MAX_ID_RASPORED_SOBA:
                          int id2 = Controller.getInstance().vratiMaxIDRasporedSoba();
                          response.setResult(id2);
                          break;
                          
                      case KRAJ_RADA:
                          Zaposleni z = (Zaposleni) request.getArgument();
                          Controller.getInstance().getPrijavljeniZaposleni().remove(z);
                          kraj = true;
                          break;
                      
                      case KRAJ_RADA_LOGIN:
                          kraj = true;
                          System.out.println("Jedan od zaposlenih se isključio");
                          break;
                          
                      case VRATI_RASPOREDE_SOBA_PO_ID_REZERVACIJE:
                           Rezervacija rezervacija2 = (Rezervacija) request.getArgument();
                           List<AbstractDomainObject> rasporediSobaPoIdRezervacije = Controller.getInstance().ucitajRasporedeSobaPoRezervaciji(rezervacija2);
                           response.setResult(rasporediSobaPoIdRezervacije);                            
                           break;
                           
                      case AZURIRAJ_REZERVACIJU:  
                            Rezervacija rezervacija3 = (Rezervacija) request.getArgument();
                            Controller.getInstance().izmeniRezervaciju(rezervacija3);
                            response.setResult(null);
                            break;
                            
                      case VRATI_REZERVACIJE:
                          Korisnik korisnik5 = (Korisnik) request.getArgument();
                          List<Rezervacija> rezervacije4 = Controller.getInstance().vratiRezervacije2(korisnik5);
                          response.setResult(rezervacije4);
                          break;
                          
                      case VRATI_RASPOREDE_SOBA_PO_ID_KORISNIKA:
                           Korisnik korisnik6 = (Korisnik) request.getArgument();
                           List<AbstractDomainObject> rasporediSobaPoIdKorisnika = Controller.getInstance().ucitajRasporedeSobaPoKorisniku(korisnik6);
                           response.setResult(rasporediSobaPoIdKorisnika);                            
                           break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setException(e);
               
                }
                sender.send(response);
            } catch (Exception ex) {
                ex.printStackTrace();
                
            }
        }
    }
    

    public void kraj() throws Exception {
      kraj = true;
     
    }
}
