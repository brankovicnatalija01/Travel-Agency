/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.controller.aranzman;

import communication.Communication;
import domain.Aranzman;

import domain.Destinacija;
import domain.TipAranzmana;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import view.coordinator.ClientFormCoordinator;
import view.form.aranzman.AranzmanFrm;
import view.form.util.FormMode;
import static view.form.util.FormMode.FORM_ADD;
import static view.form.util.FormMode.FORM_EDIT;


/**
 *
 * @author natalija
 */
public class AranzmanFrmController {
    
    private final AranzmanFrm aranzmanFrm;
   

    public AranzmanFrmController(AranzmanFrm aranžmanFrm) {
        this.aranzmanFrm = aranžmanFrm;
        addActionListeners();
    }

    public void openForm(FormMode mod) throws Exception {
        aranzmanFrm.setVisible(true);
      //  aranzmanFrm.setLocationRelativeTo(null);
        popuniCbTipAranzmana();
        popuniCbDestinacija();
        aranzmanFrm.setTitle("Glavna forma Aranžman");
        prepareForm(mod);
    }

    private void addActionListeners() {
       aranzmanFrm.dodajAranzmanBtnAddActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) {
             dodajAranzman(ae);
                     
           }
           
            private void dodajAranzman(ActionEvent ae) {

                     DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                     
                     TipAranzmana tipAranzmana = (TipAranzmana) aranzmanFrm.getjComboBoxTipAranžmana().getSelectedItem();
                     String datumOdS = aranzmanFrm.getjTextFieldDatumOd().getText().trim();
                     String datumDoS = aranzmanFrm.getjTextFieldDatumDo().getText().trim();
                     Destinacija destinacija = (Destinacija) aranzmanFrm.getjComboBoxDestinacija().getSelectedItem();
                   
                     if(validateForm(tipAranzmana, datumOdS, datumDoS, destinacija)) {
                         try {
                             
                             java.sql.Date datumOd = java.sql.Date.valueOf(datumOdS);
                             java.sql.Date datumDo = java.sql.Date.valueOf(datumDoS);
                             
                             Aranzman aranzman = new Aranzman(-1, tipAranzmana, datumOd, datumDo, destinacija);
                             Communication.getInstance().dodajAranzman(aranzman);
                             
                             JOptionPane.showMessageDialog(aranzmanFrm, "Sistem је uspešno kreirao novi aranžman", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                             aranzmanFrm.dispose();
                             
                            }  catch (Exception ex) {
                                if (ex instanceof java.net.SocketException | ex instanceof java.lang.Exception) {
                                    JOptionPane.showMessageDialog(null, "Komunikacija izmedju servera je prekinuta");
                                    aranzmanFrm.dispose();
                                    System.exit(0);
                                } else {
                                    Logger.getLogger(AranzmanFrmController.class.getName()).log(Level.SEVERE, null, ex);
                                    JOptionPane.showMessageDialog(aranzmanFrm, "Sistem ne može da kreira novi aranžman", "Greška", JOptionPane.ERROR_MESSAGE);
                                    aranzmanFrm.dispose();
                                  }}
                    } else {
                         JOptionPane.showMessageDialog(aranzmanFrm, "Sistem ne može da kreira novi aranžman", "Greška", JOptionPane.ERROR_MESSAGE);
                     }}

      
        });
       
       aranzmanFrm.izmeniAranzmanBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeniAranzman();
            }

            private void izmeniAranzman() {
                
                     DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                     
                     int id = Integer.parseInt(aranzmanFrm.getjTextFieldIDAranžmana().getText());
                     TipAranzmana tipAranzmana = (TipAranzmana) aranzmanFrm.getjComboBoxTipAranžmana().getSelectedItem();
                     String datumOdS = aranzmanFrm.getjTextFieldDatumOd().getText().trim();
                     String datumDoS = aranzmanFrm.getjTextFieldDatumDo().getText().trim();
                     Destinacija destinacija = (Destinacija) aranzmanFrm.getjComboBoxDestinacija().getSelectedItem();
                   
                     if(validateForm(tipAranzmana, datumOdS, datumDoS, destinacija)) {
                         try {
                             
                             java.sql.Date datumOd = java.sql.Date.valueOf(datumOdS);
                             java.sql.Date datumDo = java.sql.Date.valueOf(datumDoS);
                             
                             Aranzman aranzman = new Aranzman(id, tipAranzmana, datumOd, datumDo, destinacija);
                             Communication.getInstance().izmeniAranzman(aranzman);
                             ClientFormCoordinator.getInstance().osveziFormuPretragaAranzmana();
                             JOptionPane.showMessageDialog(aranzmanFrm, "Sistem je uspešno izmenio podatke o aranžmanu", "Uspeh", JOptionPane.INFORMATION_MESSAGE);                         
                             aranzmanFrm.dispose();            
                            }

                        catch (Exception ex) {
                            if (ex instanceof java.net.SocketException | ex instanceof java.lang.Exception) {
                                JOptionPane.showMessageDialog(null, "Komunikacija izmedju servera je prekinuta");
                                aranzmanFrm.dispose();
                                System.exit(0);
                            } else {
                                Logger.getLogger(AranzmanFrmController.class.getName()).log(Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(aranzmanFrm, "Sistem ne može da izmeni podatke o aranžmanu", "Greška", JOptionPane.ERROR_MESSAGE);
                                aranzmanFrm.dispose();
                                }}
                   
            } 
                     else {
                         JOptionPane.showMessageDialog(aranzmanFrm, "Sistem ne može da izmeni podatke o aranžmanu", "Greška", JOptionPane.ERROR_MESSAGE);
                         return;
                     }
            
            }
        });
       
       aranzmanFrm.obrisiBtnAddActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) {
               
              Aranzman aranzman = new Aranzman();
              aranzman.setAranzmanID(Integer.parseInt(aranzmanFrm.getjTextFieldIDAranžmana().getText()));
              aranzman.setTipAranzmana((TipAranzmana) aranzmanFrm.getjComboBoxTipAranžmana().getSelectedItem());
              String datumOdS = aranzmanFrm.getjTextFieldDatumOd().getText().trim();
              String datumDoS = aranzmanFrm.getjTextFieldDatumDo().getText().trim();
              aranzman.setDatumOd(java.sql.Date.valueOf(datumOdS));
              aranzman.setDatumDo(java.sql.Date.valueOf(datumDoS));
              aranzman.setDestinacija((Destinacija) aranzmanFrm.getjComboBoxDestinacija().getSelectedItem());
        
              
                if (aranzman == null) {
                        JOptionPane.showMessageDialog(aranzmanFrm, "Sistem ne može da obriše aranžman", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
              
                  try {
                    Communication.getInstance().obrisiAranzman(aranzman);
                    JOptionPane.showMessageDialog(aranzmanFrm, "Sistem je uspešno obrisao aranžman", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                  
                    aranzmanFrm.dispose();
                   } catch(Exception ex) {
                   
                       if(ex instanceof java.sql.SQLIntegrityConstraintViolationException) {
                           Logger.getLogger(AranzmanFrmController.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(aranzmanFrm, "Sistem ne može da obriše aranžman", "Greška", JOptionPane.ERROR_MESSAGE);
}
                     else if (ex instanceof java.net.SocketException | ex instanceof java.lang.Exception) {
                            Logger.getLogger(AranzmanFrmController.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(null, "Komunikacija izmedju servera je prekinuta");
                            aranzmanFrm.dispose();
                            System.exit(0);
                        } else {
                            Logger.getLogger(AranzmanFrmController.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(aranzmanFrm, "Sistem ne može da obriše aranžman", "Greška", JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                            }  
              }
                   }
            
           
       });
    }
    
    private void prepareForm(FormMode mod) throws Exception {
       switch(mod) {
           case FORM_ADD:
               aranzmanFrm.getjTextFieldIDAranžmana().setEnabled(false);
               aranzmanFrm.getjTextFieldIDAranžmana().setVisible(false);
               aranzmanFrm.getjLabelID().setVisible(false);
               aranzmanFrm.getjButtonIzmeniAranžman().setVisible(false);
               aranzmanFrm.getjButtonDodajAranžman().setVisible(true);
               aranzmanFrm.getjButtonObrisi().setVisible(false);
               break;
               
            case FORM_EDIT:
               aranzmanFrm.getjButtonIzmeniAranžman().setVisible(true);
               aranzmanFrm.getjButtonDodajAranžman().setVisible(false);
               aranzmanFrm.getjTextFieldIDAranžmana().setVisible(true);
               aranzmanFrm.getjLabelID().setVisible(true);
               aranzmanFrm.getjButtonObrisi().setVisible(true);
                      
               Aranzman aranzman = (Aranzman) ClientFormCoordinator.getInstance().getParam("aranzman");
               
               aranzmanFrm.getjTextFieldIDAranžmana().setText(aranzman.getAranzmanID() + "");
               aranzmanFrm.getjTextFieldDatumDo().setText(String.valueOf(aranzman.getDatumDo()));
               aranzmanFrm.getjTextFieldDatumOd().setText(String.valueOf(aranzman.getDatumOd()));
               aranzmanFrm.getjTextFieldIDAranžmana().setEnabled(false);
               aranzmanFrm.getjComboBoxTipAranžmana().setSelectedItem(aranzman.getTipAranzmana());
               aranzmanFrm.getjComboBoxDestinacija().setSelectedItem(aranzman.getDestinacija());          
               break;
               
            default:
                throw new AssertionError();
       }
    }
  
    private void popuniCbTipAranzmana() throws Exception {
        TipAranzmana[] tipoviAranzmana = TipAranzmana.values();
        
        for(TipAranzmana ta : tipoviAranzmana) {
            aranzmanFrm.getjComboBoxTipAranžmana().addItem(ta);
        }
    }
    
     private void popuniCbDestinacija() throws Exception {
        List<Destinacija> destinacije = Communication.getInstance().ucitajDestinacije();
        aranzmanFrm.getjComboBoxDestinacija().setModel(new DefaultComboBoxModel<>(destinacije.toArray()));
      
    }
    
      public boolean validateForm(TipAranzmana tipAranzmana, String datumOdS, String datumDoS, Destinacija destinacija) {
            boolean signal = true;

             if(tipAranzmana == null) {
                 JOptionPane.showMessageDialog(aranzmanFrm, "Niste odabrali tip aranžmana", "Greška", JOptionPane.ERROR_MESSAGE);
                 return signal = false;
             }

             if(destinacija == null) {
                 JOptionPane.showMessageDialog(aranzmanFrm, "Niste odabrali destinaciju", "Greška", JOptionPane.ERROR_MESSAGE);
                 return signal = false;
             }

             if(datumOdS.isEmpty() || datumDoS.isEmpty()) {
                 JOptionPane.showMessageDialog(aranzmanFrm, "Niste uneli oba datuma 'od' i 'do'", "Greška", JOptionPane.ERROR_MESSAGE);
                 return signal = false;
             }

             if (!proveriDatum(datumOdS) || !proveriDatum(datumDoS)) {
                 JOptionPane.showMessageDialog(aranzmanFrm, "Datum mora biti unet u formatu godina-mesec-dan (yyyy-MM-dd)", "Greška", JOptionPane.ERROR_MESSAGE);
                 return signal = false;
             }

             String[] deloviDatumaOd = datumOdS.split("-");
             String[] deloviDatumaDo = datumDoS.split("-");

             if (deloviDatumaOd.length != 3 || !proveraDelovaDatuma(deloviDatumaOd) || deloviDatumaDo.length != 3 || !proveraDelovaDatuma(deloviDatumaDo)) {
                 return signal = false;
             }

             LocalDate datumOd = LocalDate.parse(datumOdS);
             LocalDate datumDo = LocalDate.parse(datumDoS);

             if (datumOd.isAfter(datumDo)) {
                 JOptionPane.showMessageDialog(aranzmanFrm, "Datum 'od' mora biti pre datuma 'do'", "Greška", JOptionPane.ERROR_MESSAGE);
                 return signal = false;
             }
    
            return signal;
}


        public boolean proveriDatum(String datumOdS) {
               try {
                     LocalDate datumOd = LocalDate.parse(datumOdS);

                    } catch (Exception ex) {
                        return false;
                    }
               
                return true;
           }

           public boolean proveraDelovaDatuma(String[] deloviDatuma) {
              int godina = Integer.parseInt(deloviDatuma[0]);
              int mesec = Integer.parseInt(deloviDatuma[1]);
              int dan = Integer.parseInt(deloviDatuma[2]);

                int year = Calendar.getInstance().get(Calendar.YEAR);
                
                if (godina < year) {
                    JOptionPane.showMessageDialog(aranzmanFrm, "Niste lepo uneli godinu", "Greška", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if (mesec < 1 || mesec > 12) {
                    JOptionPane.showMessageDialog(aranzmanFrm, "Niste lepo uneli mesec", "Greška", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if (mesec == 2) {
                    if (dan < 1 || dan > 29) {
                        JOptionPane.showMessageDialog(aranzmanFrm, "Niste lepo uneli dan", "Greška", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
                if (mesec == 1 || mesec == 3 || mesec == 5 || mesec == 7 || mesec == 8 || mesec == 10 || mesec == 12) {
                    if (dan < 1 || dan > 31) {
                        JOptionPane.showMessageDialog(aranzmanFrm, "Niste lepo uneli dan", "Greška", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
                if (mesec == 4 || mesec == 6 || mesec == 9 || mesec == 11) {
                    if (dan < 1 || dan > 30) {
                        JOptionPane.showMessageDialog(aranzmanFrm, "Niste lepo uneli dan", "Greška", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }

                return true;
           }
    

}
