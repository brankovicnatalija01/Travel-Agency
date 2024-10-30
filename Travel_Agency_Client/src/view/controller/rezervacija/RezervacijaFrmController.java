/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.controller.rezervacija;

import communication.Communication;
import domain.Korisnik;
import domain.RasporedSoba;
import domain.Rezervacija;
import domain.Zaposleni;
import domain.Aranzman;
import domain.Destinacija;
import domain.TipAranzmana;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.controller.aranzman.AranzmanFrmController;
import view.coordinator.ClientFormCoordinator;
import view.form.components.tableModel.AranzmanTableModel;
import view.form.components.tableModel.RasporedSobaTableModel;
import view.form.rezervacija.RezervacijaFrm;
import view.form.util.FormMode;
import static view.form.util.FormMode.FORM_ADD;
import static view.form.util.FormMode.FORM_EDIT;

/**
 *
 * @author natalija
 */
public class RezervacijaFrmController {
    private final RezervacijaFrm rezervacijaFrm;
    private AranzmanTableModel atm;
    private RasporedSobaTableModel rstm;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Zaposleni zaposleni = ClientFormCoordinator.getInstance().getUlogovani();
    int brojRasporeda = 0;

    public RezervacijaFrmController(RezervacijaFrm rezervacijaFrm) {
        this.rezervacijaFrm = rezervacijaFrm;
        addActionListener();
    }

       public void openForm(FormMode mod) throws Exception {
        rezervacijaFrm.setVisible(true);
        rezervacijaFrm.setTitle("Glavna forma Rezervacija");
        //rezervacijaFrm.setLocationRelativeTo(null);
        pripremiFormu();
        prepareForm(mod);
        rezervacijaFrm.getjTextFieldZaposleni().setText(zaposleni.toString());
    }
       
    private void addActionListener() {
       rezervacijaFrm.dodajRasporedBtnAddActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) {
              dodajRaspored(ae);
           }

           private void dodajRaspored(ActionEvent ae) {
               try {
                   RasporedSoba rs = new RasporedSoba();
                   int maxID = Communication.getInstance().rasporedSobaMaxID();
                   
                   int rasporedSobaID = maxID + brojRasporeda;
                   
                   brojRasporeda = brojRasporeda + 1;
                   
                   Korisnik korisnik = (Korisnik) rezervacijaFrm.getjComboBoxKorisnik().getSelectedItem();
                   String osiguranje = (String) rezervacijaFrm.getjComboBoxOsiguranje().getSelectedItem();
                   double ukupnaCena = 0;
                           
                   try {
                       ukupnaCena = Double.parseDouble(rezervacijaFrm.getjTextFieldUkupnaCena().getText().trim());
                   } catch (NumberFormatException e) {
                       JOptionPane.showMessageDialog(rezervacijaFrm, "Niste lepo uneli ukupnu cenu", "Greška", JOptionPane.ERROR_MESSAGE);
                       return;
                   }
                 
                   Rezervacija rezervacija = new Rezervacija();
                   int rezervacijaID = Communication.getInstance().rezervacijaMaxID();
                   rezervacija.setRezervacijaID(rezervacijaID);
                   
                   rs.setRasporedSobaID(rasporedSobaID);
                   rs.setRezervacija(rezervacija);
                   rs.setKorisnik(korisnik);
                   rs.setOsiguranje(osiguranje);
                   rs.setUkupnaCena(ukupnaCena);
                   
                   rstm = (RasporedSobaTableModel) rezervacijaFrm.getjTableRasporedSoba().getModel();
                   
                   List<RasporedSoba> rasporediSoba = rstm.getLista();
                   
                   for(RasporedSoba r : rasporediSoba) {
                       if(r.getKorisnik().equals(korisnik)){
                            JOptionPane.showMessageDialog(rezervacijaFrm, "Raspored soba za izabranog korisnika je već dodat", "Greška", JOptionPane.ERROR_MESSAGE);
                            return;
                   }}
                       
                     rstm.dodajRaspored(rs);
                     System.out.println(".dodajRaspored()" + rs.toString());
                     rezervacijaFrm.getjTextFieldUkupnaCena().setText("");
                     rezervacijaFrm.getjComboBoxKorisnik().setSelectedIndex(1);
                           
                 } catch (Exception ex) {
                    if (ex instanceof java.net.SocketException | ex instanceof java.lang.Exception) {
                            JOptionPane.showMessageDialog(null, "Komunikacija izmedju servera je prekinuta");
                            rezervacijaFrm.dispose();
                            System.exit(0);
                    } else {
                            Logger.getLogger(RezervacijaFrmController.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(rezervacijaFrm, "Sistem ne može da zapamti raspored soba", "Greška", JOptionPane.ERROR_MESSAGE);
                            return;
                    }
               }
               
           }
       });
       rezervacijaFrm.obrisiRasporedBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiRaspored();
            }

            private void obrisiRaspored() {
                int red = rezervacijaFrm.getjTableRasporedSoba().getSelectedRow();
                rstm = (RasporedSobaTableModel) rezervacijaFrm.getjTableRasporedSoba().getModel();
                
                if (red >= 0) {
                    rstm.obrisiRaspored(red);
                    rstm.osvezi();
                } else {
                    JOptionPane.showMessageDialog(rezervacijaFrm, "Morate izabrati raspored soba koji želite da obrišete", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
       
       rezervacijaFrm.addOtkaziBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rezervacijaFrm.dispose();
            }
        });
       
       rezervacijaFrm.addSacuvajBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvajRezervaciju();
            }

           private void sacuvajRezervaciju() {
               
                try {
                    Rezervacija rezervacija = new Rezervacija();
                    int rezervacijaID = Communication.getInstance().rezervacijaMaxID();
                    
                    String sifraRezervacije = rezervacijaFrm.getjTextFieldSifraRezervacije().getText().trim();
                    String tipSobe = (String) rezervacijaFrm.getjComboBoxTipSobe().getSelectedItem();
                    String datum = rezervacijaFrm.getjTextFieldDatumRezervacije().getText().trim();
 
                    atm = (AranzmanTableModel) rezervacijaFrm.getjTableAranzman().getModel();
                    
                    int red = rezervacijaFrm.getjTableAranzman().getSelectedRow();
                    Aranzman aranzman;
                    
                    if (red >= 0) {
                        aranzman = atm.getAranzmanAt(red);
                        rezervacija.setAranzman(aranzman);
                    } else {
                        JOptionPane.showMessageDialog(rezervacijaFrm, "Morate izabrati aranžman", "Greška", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(rezervacijaFrm, "Sistem ne može da kreira novu rezervaciju", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    rstm = (RasporedSobaTableModel) rezervacijaFrm.getjTableRasporedSoba().getModel();
                    List<RasporedSoba> rasporediSoba = rstm.getLista();
 
                    if (sifraRezervacije.isEmpty()) {                  
                        JOptionPane.showMessageDialog(rezervacijaFrm, "Niste uneli naziv šifru rezervacije", "Greška", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(rezervacijaFrm, "Sistem ne može da kreira novu rezervaciju", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (datum.isEmpty() || !(proveriDatum(datum))) {
                        JOptionPane.showMessageDialog(rezervacijaFrm, "Datum mora biti u formatu godina-mesec-dan (yyyy-MM-dd)", "Greška", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(rezervacijaFrm, "Sistem ne može da kreira novu rezervaciju", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (rasporediSoba.isEmpty()) {
                        JOptionPane.showMessageDialog(rezervacijaFrm, "Morate uneti bar jedan raspored soba za rezervaciju", "Greška", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(rezervacijaFrm, "Sistem ne može da kreira novu rezervaciju", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                   
                    java.sql.Date datumRezervacije = java.sql.Date.valueOf(datum);
                    
                    rezervacija.setRezervacijaID(rezervacijaID);
                    rezervacija.setSifraRezervacije(sifraRezervacije);
                    rezervacija.setTipSobe(tipSobe);
                    rezervacija.setDatumRezervacije(datumRezervacije);
                    rezervacija.setZaposleni(zaposleni);
                    rezervacija.setAranzman(aranzman);
                    rezervacija.setRasporediSoba(rasporediSoba);
                    
                    Communication.getInstance().dodajRezervaciju(rezervacija);
                    brojRasporeda = 0;
                    JOptionPane.showMessageDialog(rezervacijaFrm, "Sistem je uspešno kreirao novu rezervaciju", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    rezervacijaFrm.dispose();
                } catch (Exception ex) {
                    if (ex instanceof java.net.SocketException | ex instanceof java.lang.Exception) {
                            JOptionPane.showMessageDialog(null, "Komunikacija izmedju servera je prekinuta");
                            rezervacijaFrm.dispose();
                            System.exit(0);
                    } else {
                            Logger.getLogger(RezervacijaFrmController.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(rezervacijaFrm, "Sistem ne može da kreira novu rezervaciju", "Greška", JOptionPane.ERROR_MESSAGE);
                            return;
                    }
                }
               }
          });
       
       rezervacijaFrm.izmeniRezervacijuBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeniRezervaciju();
            }

            private void izmeniRezervaciju() {
                try {
                    Rezervacija rezervacija = new Rezervacija();
                    int rezervacijaID = Integer.parseInt(rezervacijaFrm.getjTextFieldIDRezervacije().getText().trim());
                    String sifraRezervacije = rezervacijaFrm.getjTextFieldSifraRezervacije().getText().trim();
                    String tipSobe = (String) rezervacijaFrm.getjComboBoxTipSobe().getSelectedItem();
                    String datum = rezervacijaFrm.getjTextFieldDatumRezervacije().getText().trim();
 
                    atm = (AranzmanTableModel) rezervacijaFrm.getjTableAranzman().getModel();
                    
                    int red = rezervacijaFrm.getjTableAranzman().getSelectedRow();
                    Aranzman aranzman;
                    
                    if (red >= 0) {
                        aranzman = atm.getAranzmanAt(red);
                        rezervacija.setAranzman(aranzman);
                    } else {
                        JOptionPane.showMessageDialog(rezervacijaFrm, "Morate izabrati aranžman", "Greška", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(rezervacijaFrm, "Sistem ne može da izmeni podatke o rezervaciji", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    rstm = (RasporedSobaTableModel) rezervacijaFrm.getjTableRasporedSoba().getModel();
                    List<RasporedSoba> rasporediSoba = rstm.getLista();
                    
                    if (sifraRezervacije.isEmpty()) {                  
                        JOptionPane.showMessageDialog(rezervacijaFrm, "Niste uneli naziv šifru rezervacije", "Greška", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(rezervacijaFrm, "Sistem ne može da izmeni podatke o rezervaciji", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (datum.isEmpty() || !(proveriDatum(datum))) {
                        JOptionPane.showMessageDialog(rezervacijaFrm, "Datum mora biti u formatu godina-mesec-dan (yyyy-MM-dd)", "Greška", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(rezervacijaFrm, "Sistem ne može da izmeni podatke o rezervaciji", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (rasporediSoba.isEmpty()) {
                        JOptionPane.showMessageDialog(rezervacijaFrm, "Morate uneti bar jedan raspored soba za rezervaciju", "Greška", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(rezervacijaFrm, "Sistem ne može da izmeni podatke o rezervaciji", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                   
                    java.sql.Date datumRezervacije = java.sql.Date.valueOf(datum);
                    
                    rezervacija.setRezervacijaID(rezervacijaID);
                    rezervacija.setSifraRezervacije(sifraRezervacije);
                    rezervacija.setTipSobe(tipSobe);
                    rezervacija.setDatumRezervacije(datumRezervacije);
                    rezervacija.setZaposleni(zaposleni);
                    rezervacija.setAranzman(aranzman);
                    rezervacija.setRasporediSoba(rasporediSoba);
                    
                    Communication.getInstance().izmeniRezervaciju(rezervacija);
                    JOptionPane.showMessageDialog(rezervacijaFrm, "Sistem je uspešno izmenio podatke o rezervaciji", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    rezervacijaFrm.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(RezervacijaFrmController.class.getName()).log(Level.SEVERE, null, ex);
                     JOptionPane.showMessageDialog(rezervacijaFrm, "Sistem ne može da izmeni podatke o rezervaciji", "Greška", JOptionPane.INFORMATION_MESSAGE);
                }
            
            }
        });
    }
     
     public boolean proveriDatum(String datumOdS) {
         try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date date = df.parse(datumOdS);
                    
            } catch (ParseException ex) {
                return false;
            }           
                return true;
           }

           public boolean proveraDelovaDatuma(String[] deloviDatuma) {
              int godina = Integer.parseInt(deloviDatuma[0]);
              int mesec = Integer.parseInt(deloviDatuma[1]);
              int dan = Integer.parseInt(deloviDatuma[2]);

                int year = Calendar.getInstance().get(Calendar.YEAR);
                
                if (godina > year) {
                    JOptionPane.showMessageDialog(rezervacijaFrm, "Niste lepo uneli godinu", "Greška", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if (mesec < 1 || mesec > 12) {
                    JOptionPane.showMessageDialog(rezervacijaFrm, "Niste lepo uneli mesec", "Greška", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if (mesec == 2) {
                    if (dan < 1 || dan > 29) {
                        JOptionPane.showMessageDialog(rezervacijaFrm, "Niste lepo uneli dan", "Greška", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
                if (mesec == 1 || mesec == 3 || mesec == 5 || mesec == 7 || mesec == 8 || mesec == 10 || mesec == 12) {
                    if (dan < 1 || dan > 31) {
                        JOptionPane.showMessageDialog(rezervacijaFrm, "Niste lepo uneli dan", "Greška", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
                if (mesec == 4 || mesec == 6 || mesec == 9 || mesec == 11) {
                    if (dan < 1 || dan > 30) {
                        JOptionPane.showMessageDialog(rezervacijaFrm, "Niste lepo uneli dan", "Greška", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }

                return true;
           }

    private void pripremiFormu() throws Exception {
        
        try {
            List<RasporedSoba> rasporediSoba = new ArrayList<>();
            rstm = new RasporedSobaTableModel(rasporediSoba);
            rezervacijaFrm.getjTableRasporedSoba().setModel(rstm);

            List<Aranzman> aranzmani = Communication.getInstance().ucitajAranzmane();
            AranzmanTableModel atm = new AranzmanTableModel(aranzmani);
            rezervacijaFrm.getjTableAranzman().setModel(atm);

            List<Korisnik> korisnici = Communication.getInstance().ucitajKorisnike();

            for(Korisnik k : korisnici) {
                rezervacijaFrm.getjComboBoxKorisnik().addItem(k);
            }
        } catch (Exception ex) {
            if (ex instanceof java.net.SocketException | ex instanceof java.lang.Exception) {
                JOptionPane.showMessageDialog(null, "Komunikacija izmedju servera je prekinuta");
                rezervacijaFrm.dispose();
                System.exit(0);
                }
        }
       
    }
    
    private void prepareForm(FormMode mod) throws Exception {
       switch(mod) {
           case FORM_ADD:
               rezervacijaFrm.getjTextFieldIDRezervacije().setEnabled(false);
               rezervacijaFrm.getjTextFieldIDRezervacije().setVisible(false);
               rezervacijaFrm.getjLabelID().setVisible(false);
               rezervacijaFrm.getjButtonIzmeni().setVisible(false);
               rezervacijaFrm.getjButtonSacuvajRezervaciju().setVisible(true);
               rezervacijaFrm.getjButtonOtkazi().setVisible(true);

               break;
               
            case FORM_EDIT:
               rezervacijaFrm.getjTextFieldIDRezervacije().setEnabled(false);
               rezervacijaFrm.getjTextFieldIDRezervacije().setVisible(false);
               rezervacijaFrm.getjLabelID().setVisible(false);
               rezervacijaFrm.getjButtonIzmeni().setVisible(true);
               rezervacijaFrm.getjButtonSacuvajRezervaciju().setVisible(false);
               rezervacijaFrm.getjButtonOtkazi().setVisible(false);

                      
               Rezervacija rezervacija = (Rezervacija) ClientFormCoordinator.getInstance().getParam("rezervacija");
               
               rezervacijaFrm.getjTextFieldIDRezervacije().setText(rezervacija.getRezervacijaID() + "");
               rezervacijaFrm.getjTextFieldZaposleni().setText(rezervacija.getZaposleni().toString() + "");
               rezervacijaFrm.getjTextFieldDatumRezervacije().setText(String.valueOf(rezervacija.getDatumRezervacije()));
               rezervacijaFrm.getjTextFieldSifraRezervacije().setText(rezervacija.getSifraRezervacije());
               rezervacijaFrm.getjComboBoxTipSobe().setSelectedItem(rezervacija.getTipSobe());
               
                Aranzman aranzman = rezervacija.getAranzman();
                AranzmanTableModel model = (AranzmanTableModel) rezervacijaFrm.getjTableAranzman().getModel();

                int rowCount = model.getRowCount();
                for (int i = 0; i < rowCount; i++) {
                    Aranzman currentAranzman = model.getAranzmanAt(i);
                    if (currentAranzman.equals(aranzman)) {
                        // Postavljanje selekcije na pronađeni red
                        rezervacijaFrm.getjTableAranzman().setRowSelectionInterval(i, i);
                        break;
                    }
                }
                
                List<RasporedSoba> rasporediSoba = Communication.getInstance().vratiRasporedSobePoIDRezervacije(rezervacija);
                rstm = new RasporedSobaTableModel(rasporediSoba);
                rezervacijaFrm.getjTableRasporedSoba().setModel(rstm);
               break;
               
            default:
                throw new AssertionError();
       }
    }

   
    
}
