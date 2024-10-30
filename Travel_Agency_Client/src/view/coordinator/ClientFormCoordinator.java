/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.coordinator;

import domain.Zaposleni;
import java.util.HashMap;
import java.util.Map;
import view.controller.aranzman.AranzmanFrmController;
import view.controller.korisnik.KorisnikFrmController;
import view.controller.LoginFrmController;
import view.controller.MainFrmController;
import view.controller.aranzman.PretragaAranzmanaFrmController;
import view.controller.korisnik.PretragaKorisnikaFrmController;
import view.controller.rezervacija.PretragaRezervacijaFrmController;
import view.controller.rezervacija.RezervacijaFrmController;
import view.form.aranzman.AranzmanFrm;
import view.form.LoginFrm;
import view.form.MainFrm;
import view.form.aranzman.PretragaAranzmanaFrm;
import view.form.korisnik.KorisnikFrm;
import view.form.korisnik.PretragaKorisnikaFrm;
import view.form.rezervacija.PretragaRezervacijaFrm;
import view.form.rezervacija.RezervacijaFrm;
import view.form.util.FormMode;

/**
 *
 * @author natalija
 */
public class ClientFormCoordinator {
    
    private static ClientFormCoordinator instance;
    MainFrmController mainFrmContoller; 
    PretragaKorisnikaFrmController pretragaKorisnikaFrmController;
    PretragaAranzmanaFrmController pretragaAranžmanaFrmController;
    Zaposleni ulogovani;
    private final Map<String, Object> params;

    private ClientFormCoordinator() {
        mainFrmContoller = new MainFrmController(new MainFrm());
        params = new HashMap<>();
    }

    public static ClientFormCoordinator getInstance() {
        if (instance == null) {
            instance = new ClientFormCoordinator();
        }
        return instance;
    }

    public void addParam(String name, Object key) {
        params.put(name, key);
    }

    public Object getParam(String name) {
        return params.get(name);
    }

    public void openLoginForm() {
      LoginFrmController loginFrmContoller = new LoginFrmController(new LoginFrm());
      loginFrmContoller.openForm();
    }
    
    public void openMainFrm() {
       mainFrmContoller.openForm();

    }
    
    public void openKorisnikFrm() {
        KorisnikFrmController korisnikFrmController = new KorisnikFrmController(new KorisnikFrm(mainFrmContoller.getMainFrm(), false));
        korisnikFrmController.openForm(FormMode.FORM_ADD);

    }

    public void openPretragaKorisnikaFrm() throws Exception {
       pretragaKorisnikaFrmController = new PretragaKorisnikaFrmController(new PretragaKorisnikaFrm(mainFrmContoller.getMainFrm(), false));
       pretragaKorisnikaFrmController.openForm();
    }

    public void openAranzmanFrm() throws Exception {
       AranzmanFrmController aranžmanFrmController = new AranzmanFrmController(new AranzmanFrm(mainFrmContoller.getMainFrm(), false));
       aranžmanFrmController.openForm(FormMode.FORM_ADD); 
    }

    public void openPretragaAranzmanaFrm() throws Exception {
       pretragaAranžmanaFrmController = new PretragaAranzmanaFrmController(new PretragaAranzmanaFrm(mainFrmContoller.getMainFrm(), false));
       pretragaAranžmanaFrmController.openForm(); 
    }

    public Zaposleni getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Zaposleni ulogovani) {
        this.ulogovani = ulogovani;
    }

    public void openIzmeniKorisnikaFormu() {
        KorisnikFrmController korisnikFrmController = new KorisnikFrmController(new KorisnikFrm(null , false));
        korisnikFrmController.openForm(FormMode.FORM_EDIT);
    }

    public void openIzmeniAranzmanFormu() throws Exception {
        AranzmanFrmController aranzmanFrmController = new AranzmanFrmController(new AranzmanFrm(mainFrmContoller.getMainFrm(), false));
        aranzmanFrmController.openForm(FormMode.FORM_EDIT);
    }

    public void openPretragaRezervacijaFrm() throws Exception {
       PretragaRezervacijaFrmController pretragaRezervacijaFrmController = new PretragaRezervacijaFrmController(new PretragaRezervacijaFrm(mainFrmContoller.getMainFrm(), false));
       pretragaRezervacijaFrmController.openForm();
    }

    public void openRezervacijaFrm() throws Exception {
        RezervacijaFrmController rezervacijaFrmController = new RezervacijaFrmController(new RezervacijaFrm(mainFrmContoller.getMainFrm(), false));
        rezervacijaFrmController.openForm(FormMode.FORM_ADD);
    }
    public void openIzmeniRezervacijuFormu() throws Exception {
        RezervacijaFrmController rezervacijaFrmController = new RezervacijaFrmController(new RezervacijaFrm(null , false));
        rezervacijaFrmController.openForm(FormMode.FORM_EDIT);
    }

    public void osveziFormuPretragaKorisnika() throws Exception {
        pretragaKorisnikaFrmController.osveziFormu();
    }

    public void osveziFormuPretragaAranzmana() throws Exception {
        pretragaAranžmanaFrmController.osveziFormu();
    }

    public MainFrmController getMainFrmContoller() {
        return mainFrmContoller;
    }

    public void openPrikaziRezervacije() throws Exception {
        PrikaziRezervacijeController prikazi = new PrikaziRezervacijeController(new PrikaziRezervacije(null, false));
        prikazi.openForm();

    }

    

    

    
}
