/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.controller.korisnik;

import communication.Communication;
import domain.Korisnik;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.controller.LoginFrmController;
import view.coordinator.ClientFormCoordinator;
import view.form.korisnik.KorisnikFrm;
import view.form.util.FormMode;


/**
 *
 * @author natalija
 */
public class KorisnikFrmController {

    private final KorisnikFrm korisnikFrm;

    public KorisnikFrmController(KorisnikFrm korisnikFrm) {
        this.korisnikFrm = korisnikFrm;
        addActionListeners();
    }

    public void openForm(FormMode mod) {
        korisnikFrm.setVisible(true);
       // korisnikFrm.setLocationRelativeTo(null);
        korisnikFrm.setTitle("Glavna forma Korisnik");
        prepareForm(mod);
    }

    private void addActionListeners() {
       korisnikFrm.dodajKorisnikaBtnAddActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) {
             dodajKorisnika(ae);
                     
           }
            private void dodajKorisnika(ActionEvent ae) {

             
                     String ime = korisnikFrm.getjTextFieldIme().getText().trim();
                     String prezime = korisnikFrm.getjTextFieldPrezime().getText().trim();
                     String telefon = korisnikFrm.getjTextFieldTelefon().getText().trim();
                     String email = korisnikFrm.getjTextFieldEmail().getText().trim();
                    
                     boolean ispravno = validateForm(ime, prezime, telefon, email);

                     if(ispravno) {
                       try {
                          Korisnik korisnik = new Korisnik(-1, ime, prezime, telefon, email);

                          Communication.getInstance().dodajKorisnika(korisnik);
                          JOptionPane.showMessageDialog(korisnikFrm, "Sistem је uspešno kreirao novog korisnika", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                          korisnikFrm.dispose();

                    }  catch (Exception ex) {
                        if (ex instanceof java.net.SocketException | ex instanceof java.lang.Exception) {
                            JOptionPane.showMessageDialog(null, "Komunikacija izmedju servera je prekinuta");
                            korisnikFrm.dispose();
                            System.exit(0);
                        } else {
                            Logger.getLogger(KorisnikFrmController.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(korisnikFrm, "Sistem ne može da kreira novog korisnika", "Greška", JOptionPane.ERROR_MESSAGE);
  
                            }      

            }} else {
                         JOptionPane.showMessageDialog(korisnikFrm, "Sistem ne može da kreira novog korisnika", "Greška", JOptionPane.ERROR_MESSAGE);
                         return;
                         
                     }}
        });
       
       korisnikFrm.izmeniKorisnikaBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeniKorisnika();
            }

            private void izmeniKorisnika() {
      
                     int id = Integer.parseInt(korisnikFrm.getjTextFieldID().getText());
                     String ime = korisnikFrm.getjTextFieldIme().getText().trim();
                     String prezime = korisnikFrm.getjTextFieldPrezime().getText().trim();
                     String telefon = korisnikFrm.getjTextFieldTelefon().getText().trim();
                     String email = korisnikFrm.getjTextFieldEmail().getText().trim();
             
                     boolean ispravno = validateForm(ime, prezime, telefon, email);
                    
                     if(ispravno) {
                        try {
                            Korisnik korisnik = new Korisnik(id, ime, prezime, telefon, email);

                            Communication.getInstance().izmeniKorisnika(korisnik);
                            ClientFormCoordinator.getInstance().osveziFormuPretragaKorisnika();
                            JOptionPane.showMessageDialog(korisnikFrm, "Sistem je uspešno izmenio podatke o korisniku", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                            korisnikFrm.dispose();
                    }
                        catch (Exception ex) {
                         if (ex instanceof java.net.SocketException | ex instanceof java.lang.Exception) {
                            JOptionPane.showMessageDialog(null, "Komunikacija izmedju servera je prekinuta");
                            korisnikFrm.dispose();
                            System.exit(0);
                        } else {
                            Logger.getLogger(KorisnikFrmController.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(korisnikFrm, "Sistem ne može da izmeni podatke o korisniku", "Greška", JOptionPane.ERROR_MESSAGE);
                            }      
                        

                }} else {
                         JOptionPane.showMessageDialog(korisnikFrm, "Sistem ne može da izmeni podatke o korisniku", "Greška", JOptionPane.ERROR_MESSAGE);
                         return;
                     }

            }
  
        });
       
       korisnikFrm.obrisiBtnAddActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) {
               
              Korisnik korisnik = new Korisnik();
              korisnik.setKorisnikID(Integer.parseInt(korisnikFrm.getjTextFieldID().getText().trim()));
              korisnik.setIme(korisnikFrm.getjTextFieldIme().getText().trim());
              korisnik.setPrezime(korisnikFrm.getjTextFieldPrezime().getText().trim());
              korisnik.setEmail(korisnikFrm.getjTextFieldEmail().getText().trim());
              korisnik.setTelefon(korisnikFrm.getjTextFieldTelefon().getText().trim());
              
                if (korisnik == null) {
                        JOptionPane.showMessageDialog(korisnikFrm, "Sistem ne može da obriše korisnika", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                  try {
                    Communication.getInstance().obrisiKorisnika(korisnik);
                    JOptionPane.showMessageDialog(korisnikFrm, "Sistem je uspešno obrisao korisnika", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    korisnikFrm.dispose();
                   } catch(Exception ex) {
                   
                       if(ex instanceof java.sql.SQLIntegrityConstraintViolationException) {
                           Logger.getLogger(PretragaKorisnikaFrmController.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(korisnikFrm, "Sistem ne može da obriše korisnika", "Greška", JOptionPane.ERROR_MESSAGE);
}
                     else if (ex instanceof java.net.SocketException | ex instanceof java.lang.Exception) {
                            Logger.getLogger(PretragaKorisnikaFrmController.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(null, "Komunikacija izmedju servera je prekinuta");
                            korisnikFrm.dispose();
                            System.exit(0);
                        } else {
                            Logger.getLogger(PretragaKorisnikaFrmController.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(korisnikFrm, "Sistem ne može da obriše korisnika", "Greška", JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                            }  
              }
                   }
            
           
       });
       
       korisnikFrm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    korisnikFrm.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(KorisnikFrmController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        });
    }
     
    private void prepareForm(FormMode mod) {
       switch(mod) {
           case FORM_ADD:
               korisnikFrm.getjTextFieldID().setEnabled(false);
               korisnikFrm.getjTextFieldID().setVisible(false);
               korisnikFrm.getjLabelID().setVisible(false);
               korisnikFrm.getjButtonIzmeni().setVisible(false);
               korisnikFrm.getjButtonDodaj().setVisible(true);   
               korisnikFrm.getjButtonObrisiKorisnika().setVisible(false);
               break;
               
            case FORM_EDIT:
               korisnikFrm.getjButtonIzmeni().setVisible(true);
               korisnikFrm.getjButtonDodaj().setVisible(false);
               korisnikFrm.getjTextFieldID().setEnabled(false);
               korisnikFrm.getjTextFieldID().setVisible(true);
               korisnikFrm.getjLabelID().setVisible(true);
               korisnikFrm.getjButtonObrisiKorisnika().setVisible(true);
               
               Korisnik k = (Korisnik) ClientFormCoordinator.getInstance().getParam("korisnik");
               
               korisnikFrm.getjTextFieldIme().setText(k.getIme());
               korisnikFrm.getjTextFieldPrezime().setText(k.getPrezime());
               korisnikFrm.getjTextFieldTelefon().setText(k.getTelefon());
               korisnikFrm.getjTextFieldEmail().setText(k.getEmail());
               korisnikFrm.getjTextFieldID().setText(k.getKorisnikID() + ""); 
               break;
               
            default:
                throw new AssertionError();
       }
    }
     public boolean validateForm(String ime, String prezime, String telefon, String email) {
                boolean signal = true;
                   
                if (ime.isEmpty()) {
                     JOptionPane.showMessageDialog(korisnikFrm, "Niste uneli ime", "Greška", JOptionPane.ERROR_MESSAGE);
                     return signal = false;
                }
                if (prezime.isEmpty()) {
                    JOptionPane.showMessageDialog(korisnikFrm, "Niste uneli prezime", "Greška", JOptionPane.ERROR_MESSAGE);
                     return signal = false;
                }

                if (telefon.isEmpty()) {
                     JOptionPane.showMessageDialog(korisnikFrm, "Nisu uneli broj telefona", "Greška", JOptionPane.ERROR_MESSAGE);
                     return signal = false;
                }
                if (email.isEmpty() || !email.contains("@")) {
                     JOptionPane.showMessageDialog(korisnikFrm, "Mejl mora da sadrži znak @", "Greška", JOptionPane.ERROR_MESSAGE);
                     return signal = false;
                }
                
                char[] imeC = ime.toCharArray();
                char[] prezimeC = prezime.toCharArray();
                
                for (char c : imeC) {
                    if(!Character.isLetter(c)) {
                     JOptionPane.showMessageDialog(korisnikFrm, "Ime sme da sadrži samo slova", "Greška", JOptionPane.ERROR_MESSAGE);
                     return signal = false;                    }
                  }
                
                for (char c : prezimeC) {
                    if(!Character.isLetter(c)) {
                      JOptionPane.showMessageDialog(korisnikFrm, "Prezime sme da sadrži samo slova", "Greška", JOptionPane.ERROR_MESSAGE);
                      return signal = false;
                    }
                }
            
              return signal; 
           }
    
    
    
}
