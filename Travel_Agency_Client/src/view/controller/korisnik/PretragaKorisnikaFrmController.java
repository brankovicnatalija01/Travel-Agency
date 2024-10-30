/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.controller.korisnik;

import communication.Communication;
import domain.Korisnik;
import domain.RasporedSoba;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.coordinator.ClientFormCoordinator;
import view.form.components.tableModel.KorisnikTableModel;
import view.form.components.tableModel.RasporedSobaTableModel;
import view.form.korisnik.PretragaKorisnikaFrm;


/**
 *
 * @author natalija
 */
public class PretragaKorisnikaFrmController {

    private final PretragaKorisnikaFrm pretragaKorisnikaFrm;
    private KorisnikTableModel ktm;

    public PretragaKorisnikaFrmController(PretragaKorisnikaFrm korisnikFrm) {
        this.pretragaKorisnikaFrm = korisnikFrm;
        addActionListeners();
    }

    public void openForm() throws Exception {
        pretragaKorisnikaFrm.setVisible(true);
        pretragaKorisnikaFrm.setLocationRelativeTo(null);
        pretragaKorisnikaFrm.setTitle("Pretraga korisnika");
        popuniTabeluKorisnika();
    }
    
    public void popuniTabeluKorisnika() throws Exception {
        try {   
            List<Korisnik> korisnici = Communication.getInstance().ucitajKorisnike();
            KorisnikTableModel ktm = new KorisnikTableModel(korisnici);
            pretragaKorisnikaFrm.getjTableKorisnik().setModel(ktm);      
        } catch (Exception ex) {
            if (ex instanceof java.net.SocketException | ex instanceof java.lang.Exception) {
                JOptionPane.showMessageDialog(null, "Komunikacija izmedju servera je prekinuta");
                pretragaKorisnikaFrm.dispose();
                System.exit(0);
            } else {
                Logger.getLogger(KorisnikFrmController.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(pretragaKorisnikaFrm, "Sistem ne može da učita korisnike", "Greška", JOptionPane.ERROR_MESSAGE);
                pretragaKorisnikaFrm.dispose();
             }     
        }
        
    }

    private void addActionListeners() {
      
       
         pretragaKorisnikaFrm.detaljiBtnAddActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) {
              int red = pretragaKorisnikaFrm.getjTableKorisnik().getSelectedRow();
              
              if(red == -1) {
                  JOptionPane.showMessageDialog(pretragaKorisnikaFrm, "Niste odabrali korisnika", "Greška", JOptionPane.ERROR_MESSAGE);
                  JOptionPane.showMessageDialog(pretragaKorisnikaFrm, "Sistem ne može da učita korisnika", "Greška", JOptionPane.ERROR_MESSAGE);
                  return;
              } else {
                  KorisnikTableModel mtk = (KorisnikTableModel) pretragaKorisnikaFrm.getjTableKorisnik().getModel();
                  Korisnik korisnik = mtk.getLista().get(red);
                  ClientFormCoordinator.getInstance().addParam("korisnik", korisnik);
                  ClientFormCoordinator.getInstance().openIzmeniKorisnikaFormu();
                  JOptionPane.showMessageDialog(pretragaKorisnikaFrm, "Sistem je učitao korisnika", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                  try {
                      popuniTabeluKorisnika();
                  } catch (Exception ex) {
                      JOptionPane.showMessageDialog(pretragaKorisnikaFrm, "Sistem ne može da učita korisnika", "Greška", JOptionPane.ERROR_MESSAGE);
                      Logger.getLogger(PretragaKorisnikaFrmController.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
              
            
           }
       });
         
         pretragaKorisnikaFrm.prikaziBtnAddActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent ae) {
                 int red = pretragaKorisnikaFrm.getjTableKorisnik().getSelectedRow();
                 
                 if(red == -1) {
                  JOptionPane.showMessageDialog(pretragaKorisnikaFrm, "Niste odabrali korisnika", "Greška", JOptionPane.ERROR_MESSAGE);
                  JOptionPane.showMessageDialog(pretragaKorisnikaFrm, "Sistem ne može da učita korisnika", "Greška", JOptionPane.ERROR_MESSAGE);
                  return;
              } else {
                     KorisnikTableModel mtk = (KorisnikTableModel) pretragaKorisnikaFrm.getjTableKorisnik().getModel();
                     Korisnik korisnik = mtk.getLista().get(red);
                     ClientFormCoordinator.getInstance().addParam("korisnik2", korisnik);
                     try {
                         ClientFormCoordinator.getInstance().openPrikaziRezervacije();
                         
                         //List<RasporedSoba> rasporediSoba = Communication.getInstance().vratiRasporedSobePoIDRezervacije(rezervacija);
//                rstm = new RasporedSobaTableModel(rasporediSoba);
//                rezervacijaFrm.getjTableRasporedSoba().setModel(rstm);
                     } catch (Exception ex) {
                         Logger.getLogger(PretragaKorisnikaFrmController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
             }
         });

          
          pretragaKorisnikaFrm.pretragaBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String ime = pretragaKorisnikaFrm.getjTextFieldIme().getText().trim();
                    
                    if (ime.isEmpty()) {
                        JOptionPane.showMessageDialog(pretragaKorisnikaFrm, "Morate uneti ime korisnika", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    List<Korisnik> korisnici = Communication.getInstance().vratiKorisnikePoImenu(ime);
                    
                    if (korisnici.isEmpty()) {
                        popuniTabeluKorisnika();
                        JOptionPane.showMessageDialog(pretragaKorisnikaFrm, "Sistem ne može da nađe korisnike po zadatoj vrednosti", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    ktm = (KorisnikTableModel) pretragaKorisnikaFrm.getjTableKorisnik().getModel();
                    ktm.setLista(korisnici);
                    pretragaKorisnikaFrm.getjTableKorisnik().setModel(ktm);
                    
                    JOptionPane.showMessageDialog(pretragaKorisnikaFrm, "Sistem je našao korisnike po zadatoj vrednosti");
                    pretragaKorisnikaFrm.getjTextFieldIme().setText("");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    if (ex instanceof java.net.SocketException | ex instanceof java.lang.Exception) {
                            JOptionPane.showMessageDialog(null, "Komunikacija izmedju servera je prekinuta");
                            pretragaKorisnikaFrm.dispose();
                            System.exit(0);
                        } else {
                            Logger.getLogger(PretragaKorisnikaFrmController.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(pretragaKorisnikaFrm, "Sistem ne može da nađe korisnike po zadatoj vrednosti", "Greška", JOptionPane.ERROR_MESSAGE);
                            }
                    

                }

            }
        });
          
           pretragaKorisnikaFrm.resetujBtnAddActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) {
               try {
                   pretragaKorisnikaFrm.getjTextFieldIme().setText("");
                   popuniTabeluKorisnika();
               } catch (Exception ex) {
                   Logger.getLogger(PretragaKorisnikaFrmController.class.getName()).log(Level.SEVERE, null, ex);
               }
            
           }
       });
           
           pretragaKorisnikaFrm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    pretragaKorisnikaFrm.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(PretragaKorisnikaFrmController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            
        });
    }

    public void osveziFormu() throws Exception {
       popuniTabeluKorisnika();
    }
    
}
