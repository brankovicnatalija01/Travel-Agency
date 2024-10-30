/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.controller.rezervacija;

import communication.Communication;
import domain.Rezervacija;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.coordinator.ClientFormCoordinator;

import view.form.components.tableModel.RezervacijaTableModel;
import view.form.rezervacija.PretragaRezervacijaFrm;

/**
 *
 * @author natalija
 */
public class PretragaRezervacijaFrmController {
    private final PretragaRezervacijaFrm pretragaRezervacijaFrm;
    private RezervacijaTableModel rtm;

    public PretragaRezervacijaFrmController(PretragaRezervacijaFrm pretragaRezervacijaFrm) {
        this.pretragaRezervacijaFrm = pretragaRezervacijaFrm;
        addActionListeners();
    }

    public void openForm() throws Exception {
        popuniTabeluRezervacija();
        pretragaRezervacijaFrm.setVisible(true);
        pretragaRezervacijaFrm.setTitle("Pretraga rezervacija");
        pretragaRezervacijaFrm.setLocationRelativeTo(null);
    }

    private void popuniTabeluRezervacija() throws Exception {
        try {
              List<Rezervacija> rezervacije = Communication.getInstance().ucitajRezervacije();
              RezervacijaTableModel rtm = new RezervacijaTableModel(rezervacije);
              pretragaRezervacijaFrm.getjTableRezervacija().setModel(rtm);
            
        } catch (Exception ex) {
           if (ex instanceof java.net.SocketException | ex instanceof java.lang.Exception) {
                JOptionPane.showMessageDialog(null, "Komunikacija izmedju servera je prekinuta");
                pretragaRezervacijaFrm.dispose();
                System.exit(0);
           } else{
                Logger.getLogger(PretragaRezervacijaFrmController.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(pretragaRezervacijaFrm, "Sistem ne može da učita rezervacije", "Greška", JOptionPane.ERROR_MESSAGE);
                pretragaRezervacijaFrm.dispose();
           }
        }
    }
    
     private void addActionListeners() {
         
          pretragaRezervacijaFrm.detaljiBtnAddActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) {
              int red = pretragaRezervacijaFrm.getjTableRezervacija().getSelectedRow();
              
              if(red == -1) {
                  JOptionPane.showMessageDialog(pretragaRezervacijaFrm, "Niste odabrali rezervaciju", "Greška", JOptionPane.ERROR_MESSAGE);
                  JOptionPane.showMessageDialog(pretragaRezervacijaFrm, "Sistem ne može da učita rezervaciju", "Greška", JOptionPane.ERROR_MESSAGE);
              } else {
                  try {
                 
                      
                      Rezervacija rezervacija  = ((RezervacijaTableModel) pretragaRezervacijaFrm.getjTableRezervacija().getModel()).getRezervacijaAt(red);
                  
                      ClientFormCoordinator.getInstance().addParam("rezervacija", rezervacija);
                      ClientFormCoordinator.getInstance().openIzmeniRezervacijuFormu();
                      JOptionPane.showMessageDialog(pretragaRezervacijaFrm, "Sistem je učitao rezervaciju", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
         
                      try {
                          popuniTabeluRezervacija();
                      } catch (Exception ex) {
                          Logger.getLogger(PretragaRezervacijaFrmController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  } catch (Exception ex) {
                      JOptionPane.showMessageDialog(pretragaRezervacijaFrm, "Sistem ne može da učita rezervaciju", "Greška", JOptionPane.ERROR_MESSAGE);
                      Logger.getLogger(PretragaRezervacijaFrmController.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
              
            
           }
       });
          pretragaRezervacijaFrm.pretragaBtnAddActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) {
               try{
                    String sifraRezervacije = pretragaRezervacijaFrm.getjTextField1SifraRezervacije().getText().trim();
              
                    if (sifraRezervacije.isEmpty()) {
                        JOptionPane.showMessageDialog(pretragaRezervacijaFrm, "Morate uneti šifru rezervacije", "Greška", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(pretragaRezervacijaFrm, "Sistem ne može da nađe rezervacije po zadatoj vrednosti", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
               
                    List<Rezervacija> rezervacije = communication.Communication.getInstance().vratiRezervacijePoSifri(sifraRezervacije);
                    
                    if (rezervacije.isEmpty()) {
                        popuniTabeluRezervacija();
                        JOptionPane.showMessageDialog(pretragaRezervacijaFrm, "Sistem ne može da nađe rezervacije po zadatoj vrednosti", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    rtm = (RezervacijaTableModel) pretragaRezervacijaFrm.getjTableRezervacija().getModel();
                    rtm.setLista(rezervacije);
                    pretragaRezervacijaFrm.getjTableRezervacija().setModel(rtm);
                    pretragaRezervacijaFrm.getjTextField1SifraRezervacije().setText("");
                    JOptionPane.showMessageDialog(pretragaRezervacijaFrm, "Sistem je našao rezervacije po zadatoj vrednosti");
                } catch (Exception ex) {
                  if (ex instanceof java.net.SocketException | ex instanceof java.lang.Exception) {
                            JOptionPane.showMessageDialog(null, "Komunikacija izmedju servera je prekinuta");
                            pretragaRezervacijaFrm.dispose();
                            System.exit(0);
                    } else{
                      Logger.getLogger(PretragaRezervacijaFrmController.class.getName()).log(Level.SEVERE, null, ex);
                      JOptionPane.showMessageDialog(pretragaRezervacijaFrm, "Sistem ne može da nađe rezervacije po zadatoj vrednosti", "Greška", JOptionPane.ERROR_MESSAGE);
                  }
                    
                }
              }
           });
          
           pretragaRezervacijaFrm.resetujBtnAddActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) {
               try {
                   
                   pretragaRezervacijaFrm.getjTextField1SifraRezervacije().setText("");                   
                   popuniTabeluRezervacija();
               } catch (Exception ex) {
                   Logger.getLogger(PretragaRezervacijaFrmController.class.getName()).log(Level.SEVERE, null, ex);
               }
            
           }
       });
    }
     public void osveziFormu() throws Exception {
       popuniTabeluRezervacija();
    }
}
