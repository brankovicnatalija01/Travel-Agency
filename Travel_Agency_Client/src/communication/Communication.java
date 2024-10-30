/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import domain.Aranzman;
import domain.Destinacija;
import domain.Korisnik;
import domain.RasporedSoba;
import domain.Rezervacija;
import domain.Zaposleni;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import operations.Operation;
import static operations.Operation.VRATI_RASPOREDE_SOBA_PO_ID_KORISNIKA;
import view.coordinator.ClientFormCoordinator;

/**
 *
 * @author natalija
 */
public class Communication {
    private static Communication instance;
    Socket socket;
    Sender sender;
    Receiver receiver;
    String porukaLogin;

    private Communication() throws Exception {
        socket = new Socket("127.0.0.1", 9000);
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    public static Communication getInstance() throws Exception {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public Zaposleni login(String username, String password) throws Exception {
        Zaposleni zaposleni = new Zaposleni();
        
        zaposleni.setUsername(username);
        zaposleni.setPassword(password);
        
        Request request = new Request(Operation.LOGIN, zaposleni);
        
        sender.send(request);
      
        Response response = (Response) receiver.receive();
        
        if (response.getException() != null) {
            throw response.getException();
        }

        if(response.getMessage() != null) {
            porukaLogin = response.getMessage();
        }
        
        return (Zaposleni) response.getResult();       
    }

    public void dodajKorisnika(Korisnik korisnik) throws Exception {
        Request request = new Request(Operation.DODAJ_KORISNIKA, korisnik);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void izmeniKorisnika(Korisnik korisnik) throws Exception {
        Request request = new Request(Operation.AZURIRAJ_KORISNIKA, korisnik);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public List<Korisnik> ucitajKorisnike() throws Exception {
        Request request = new Request(Operation.UCITAJ_KORISNIKE, null);
        List<Korisnik> korisnici = new ArrayList<>();
        
        sender.send(request);
        
        Response response = (Response) receiver.receive();
   
        if (response.getException() == null) {
            return (List<Korisnik>) response.getResult();
        } else {
            throw response.getException();
        }
        
    }

    public void obrisiKorisnika(Korisnik korisnik) throws Exception {
        Request request = new Request(Operation.OBRISI_KORISNIKA, korisnik);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public List<Destinacija> ucitajDestinacije() throws Exception {
       Request request = new Request(Operation.UCITAJ_DESTINACIJE, null);
       List<Destinacija> destinacije = new ArrayList<>();
        
        sender.send(request);
        
        Response response = (Response) receiver.receive();
   
        if (response.getException() == null) {
            return (List<Destinacija>) response.getResult();       
        } else {
            throw response.getException();
        }
    }

    public void dodajAranzman(Aranzman aranzman) throws Exception {
        Request request = new Request(Operation.DODAJ_ARANZMAN, aranzman);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        
        if (response.getException() != null) {
              throw response.getException();
          }
    }

    public void izmeniAranzman(Aranzman aranzman) throws Exception {
        Request request = new Request(Operation.AZURIRAJ_ARANZMANE, aranzman);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public List<Aranzman> ucitajAranzmane() throws Exception {
        Request request = new Request(Operation.UCITAJ_ARANZMANE, null);
        List<Aranzman> aranzmani = new ArrayList<>();
        
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        
        if (response.getException() == null) {
            return (List<Aranzman>) response.getResult();       
        } else {
            throw response.getException();
        }
    }

    public void obrisiAranzman(Aranzman aranzman) throws Exception {
        Request request = new Request(Operation.OBRISI_ARANZMAN, aranzman);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException() != null) {
            throw response.getException();
        }
    }

    public void dodajRezervaciju(Rezervacija rezervacija) throws Exception {
        Request request = new Request(Operation.DODAJ_REZERVACIJU, rezervacija);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException() != null) {
            throw response.getException();
        }
    }

    public List<Rezervacija> ucitajRezervacije() throws Exception {
        Request request = new Request(Operation.UCITAJ_REZERVACIJE, null);
        List<Aranzman> aranzmani = new ArrayList<>();
        
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        
        if (response.getException() == null) {
            return (List<Rezervacija>) response.getResult();       
        } else {
            throw response.getException();
        }
    }

    public void krajRada() throws Exception {
       Request request = new Request(Operation.KRAJ_RADA, ClientFormCoordinator.getInstance().getUlogovani());
       sender.send(request);
    }
    
    public void krajRadaLogin() throws Exception {
        Request request = new Request(Operation.KRAJ_RADA_LOGIN, null);
        sender.send(request);
    }

    public String getPorukaLogin() {
        return porukaLogin;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public Sender getSender() {
        return sender;
    }

    public int rezervacijaMaxID() throws Exception {
        Request request = new Request(Operation.VRATI_MAX_ID_REZERVACIJE, null);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        
        if (response.getException() == null) {
            return (int) response.getResult();       
        } else {
            throw response.getException();
        }
    }

    public int rasporedSobaMaxID() throws Exception {
        Request request = new Request(Operation.VRATI_MAX_ID_RASPORED_SOBA, null);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        
        if (response.getException() == null) {
            return (int) response.getResult();       
        } else {
            throw response.getException();
        }
    }

    public List<Korisnik> vratiKorisnikePoImenu(String ime) throws Exception {
        Request request = new Request(Operation.VRATI_KORISNIKE_PO_IMENU, ime);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Korisnik>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Aranzman> vratiAranzmanePoTipu(String tipAranzmana) throws Exception {
        Request request = new Request(Operation.VRATI_ARANZMANE_PO_TIPU, tipAranzmana);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Aranzman>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Rezervacija> vratiRezervacijePoSifri(String sifraRezervacije) throws Exception {
        Request request = new Request(Operation.VRATI_REZERVACIJE_PO_SIFRI, sifraRezervacije);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Rezervacija>) response.getResult();
        } else {
            throw response.getException();
        }
    }


    public List<RasporedSoba> vratiRasporedSobePoIDRezervacije(Rezervacija rezervacija) throws Exception {
        Request request = new Request(Operation.VRATI_RASPOREDE_SOBA_PO_ID_REZERVACIJE, rezervacija);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<RasporedSoba>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void izmeniRezervaciju(Rezervacija rezervacija) throws Exception {
        Request request = new Request(Operation.AZURIRAJ_REZERVACIJU, rezervacija);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    

    public List<Rezervacija> vratiRezervacijePoIDKorisnika(Korisnik k) throws Exception {
        Request request = new Request(Operation.VRATI_REZERVACIJE, k);
        List<Aranzman> aranzmani = new ArrayList<>();
        
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        
        if (response.getException() == null) {
            return (List<Rezervacija>) response.getResult();       
        } else {
            throw response.getException();
        }
    }

    public List<RasporedSoba> vratiRasporedSobePoIDKorsinika(Korisnik k) throws Exception {
        Request request = new Request(Operation.VRATI_RASPOREDE_SOBA_PO_ID_KORISNIKA, k);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<RasporedSoba>) response.getResult();
        } else {
            throw response.getException();
        }
    }
   
}
