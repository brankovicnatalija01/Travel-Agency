/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.controller.aranzman;

import communication.Communication;
import domain.Aranzman;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.coordinator.ClientFormCoordinator;
import view.form.aranzman.PretragaAranzmanaFrm;
import view.form.components.tableModel.AranzmanTableModel;


/**
 *
 * @author natalija
 */
public class PretragaAranzmanaFrmController {

   private final PretragaAranzmanaFrm pretragaAranzmanaFrm;
   private AranzmanTableModel atm;

    public PretragaAranzmanaFrmController(PretragaAranzmanaFrm pretragaAranžmanaFrm) {
        this.pretragaAranzmanaFrm = pretragaAranžmanaFrm;
        addActionListeners();
    }

    public void openForm() throws Exception {
        PopuniTabeluAranzmana();
        pretragaAranzmanaFrm.setVisible(true);
        pretragaAranzmanaFrm.setLocationRelativeTo(null);
        pretragaAranzmanaFrm.setTitle("Pretraga Aranžmana");
    }
    
    public void PopuniTabeluAranzmana() throws Exception {
        
        try {
                List<Aranzman> aranzmani = Communication.getInstance().ucitajAranzmane();
                AranzmanTableModel atm = new AranzmanTableModel(aranzmani);
                pretragaAranzmanaFrm.getjTableAranžman().setModel(atm);

        } catch (Exception ex) {
            if (ex instanceof java.net.SocketException | ex instanceof java.lang.Exception) {
                JOptionPane.showMessageDialog(null, "Komunikacija izmedju servera je prekinuta");
                pretragaAranzmanaFrm.dispose();
                System.exit(0);
            } else {
                Logger.getLogger(PretragaAranzmanaFrmController.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(pretragaAranzmanaFrm, "Sistem ne može da učita aranžmane", "Greška", JOptionPane.ERROR_MESSAGE);
                pretragaAranzmanaFrm.dispose();
                }     
        }
    }

    private void addActionListeners() {
     
         pretragaAranzmanaFrm.detaljiBtnAddActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) {
              int red = pretragaAranzmanaFrm.getjTableAranžman().getSelectedRow();
              
              if(red == -1) {
                  JOptionPane.showMessageDialog(pretragaAranzmanaFrm, "Niste odabrali aranžman", "Greška", JOptionPane.ERROR_MESSAGE);
                  JOptionPane.showMessageDialog(pretragaAranzmanaFrm, "Sistem ne može da učita aranžman", "Greška", JOptionPane.ERROR_MESSAGE);
              } else {
                  try {
                
                      Aranzman aranzman  = ((AranzmanTableModel) pretragaAranzmanaFrm.getjTableAranžman().getModel()).getAranzmanAt(red);
                  
                      ClientFormCoordinator.getInstance().addParam("aranzman", aranzman);
                      ClientFormCoordinator.getInstance().openIzmeniAranzmanFormu();
                      JOptionPane.showMessageDialog(pretragaAranzmanaFrm, "Sistem je učitao aranžman", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
         
                      try {
                          PopuniTabeluAranzmana();
                      } catch (Exception ex) {
                          Logger.getLogger(PretragaAranzmanaFrmController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  } catch (Exception ex) {
                      JOptionPane.showMessageDialog(pretragaAranzmanaFrm, "Sistem ne može da učita aranžman", "Greška", JOptionPane.ERROR_MESSAGE);
                      Logger.getLogger(PretragaAranzmanaFrmController.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
              
            
           }
       });
          
         pretragaAranzmanaFrm.pretragaBtnAddActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) {
          try {
                    String tipAranzmana = pretragaAranzmanaFrm.getjTextFieldTipAranzmana().getText().trim();
                    
                    if (tipAranzmana.isEmpty()) {
                        JOptionPane.showMessageDialog(pretragaAranzmanaFrm, "Morate uneti tip aranžmana", "Greška", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(pretragaAranzmanaFrm, "Sistem ne može da nađe aranžmane po zadatoj vrednosti", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    List<Aranzman> aranzmani = Communication.getInstance().vratiAranzmanePoTipu(tipAranzmana);
                    
                    if (aranzmani.isEmpty()) {
                        PopuniTabeluAranzmana();
                        JOptionPane.showMessageDialog(pretragaAranzmanaFrm, "Sistem ne može da nađe aranžmane po zadatoj vrednosti", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    atm = (AranzmanTableModel) pretragaAranzmanaFrm.getjTableAranžman().getModel();
                    atm.setLista(aranzmani);
                    pretragaAranzmanaFrm.getjTableAranžman().setModel(atm);
                    
                    JOptionPane.showMessageDialog(pretragaAranzmanaFrm, "Sistem je našao aranžmane po zadatoj vrednosti");
                    pretragaAranzmanaFrm.getjTextFieldTipAranzmana().setText("");
                } catch (Exception ex) {
                    if (ex instanceof java.net.SocketException | ex instanceof java.lang.Exception) {
                        JOptionPane.showMessageDialog(null, "Komunikacija izmedju servera je prekinuta");
                        pretragaAranzmanaFrm.dispose();
                        System.exit(0);
                    } else {
                        Logger.getLogger(PretragaAranzmanaFrmController.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(pretragaAranzmanaFrm, "Sistem ne može da nađe aranžmane po zadatoj vrednosti", "Greška", JOptionPane.ERROR_MESSAGE);
                        pretragaAranzmanaFrm.dispose();
                        }          
                }
            
           }
       });
     
           pretragaAranzmanaFrm.resetujBtnAddActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) {
               try {
                   pretragaAranzmanaFrm.getjTextFieldTipAranzmana().setText("");
                   PopuniTabeluAranzmana();
               } catch (Exception ex) {
                   Logger.getLogger(PretragaAranzmanaFrmController.class.getName()).log(Level.SEVERE, null, ex);
               }
            
           }
       });
    }

    public void osveziFormu() throws Exception {
       PopuniTabeluAranzmana();
    }
    
}
