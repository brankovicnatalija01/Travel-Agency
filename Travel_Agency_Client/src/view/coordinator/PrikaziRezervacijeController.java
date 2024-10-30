package view.coordinator;


import communication.Communication;
import domain.Korisnik;
import domain.RasporedSoba;
import domain.Rezervacija;
import java.util.ArrayList;
import java.util.List;
import view.coordinator.PrikaziRezervacije;
import view.form.components.tableModel.RasporedSobaTableModel;
import view.form.components.tableModel.RezervacijaTableModel;
import view.form.rezervacija.RezervacijaFrm;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author natalija
 */
public class PrikaziRezervacijeController {
    private final PrikaziRezervacije rezervacijaFrm;
    
    public PrikaziRezervacijeController(PrikaziRezervacije rezervacijaFrm) {
        this.rezervacijaFrm = rezervacijaFrm;

    }

    void openForm() throws Exception {
        rezervacijaFrm.setVisible(true);
        Korisnik k = (Korisnik) ClientFormCoordinator.getInstance().getParam("korisnik2");
       // List<Rezervacija> rezervacije = Communication.getInstance().vratiRezervacijePoIDKorisnika(k);
        
        
       // List<RasporedSoba> rasporedi = Communication.getInstance().vratiRezervacijePoIDKorisnika(k);
       
        
      ///List<Rezervacija> rezervacije = Communication.getInstance().ucitajRezervacije();
       
      List<RasporedSoba> rs = Communication.getInstance().vratiRasporedSobePoIDKorsinika(k);
       
      List<Rezervacija> rez = Communication.getInstance().ucitajRezervacije();
      
      List<Rezervacija> rezervacijeKorinsika = new ArrayList<>();
     for(RasporedSoba r : rs) {
         for(Rezervacija rezervacija : rez) {
             if(rezervacija.getRezervacijaID() == r.getRezervacija().getRezervacijaID())
                 rezervacijeKorinsika.add(rezervacija);
         }
     }
     
     
     
//        RasporedSobaTableModel rtm = new RasporedSobaTableModel(rs);
//       rezervacijaFrm.getjTableRezervacija().setModel(rtm);
//        
    RezervacijaTableModel rtm = new RezervacijaTableModel(rezervacijeKorinsika);

      rezervacijaFrm.getjTableRezervacija().setModel(rtm);
    }
}
