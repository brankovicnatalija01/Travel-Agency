/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.controller;

import communication.Communication;
import domain.Zaposleni;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.coordinator.ClientFormCoordinator;
import view.form.MainFrm;

/**
 *
 * @author natalija
 */
public class MainFrmController {

    private final MainFrm mainFrm;

    public MainFrmController(MainFrm mainFrm) {
        this.mainFrm = mainFrm;
        addActionListeners();
    }

    public void openForm() {
        Zaposleni ulogovani = ClientFormCoordinator.getInstance().getUlogovani();
        String ime = ulogovani.getIme();
        String prezime = ulogovani.getPrezime();
        
        mainFrm.getjLabelPrijavljeniZaposleni().setText(ime + " " + prezime);
        mainFrm.setTitle("Glavna forma");
        mainFrm.setVisible(true);
    }
    
    private void addActionListeners() {

        mainFrm.kreirajKorisnikaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientFormCoordinator.getInstance().openKorisnikFrm();

            }
        });

        mainFrm.pretraziKorisnikaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    ClientFormCoordinator.getInstance().openPretragaKorisnikaFrm();
                } catch (Exception ex) {
                    Logger.getLogger(MainFrmController.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        });

        mainFrm.kreirajAranzmanAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClientFormCoordinator.getInstance().openAranzmanFrm();
                } catch (Exception ex) {
                    Logger.getLogger(MainFrmController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }
        });
        mainFrm.pretraziAranzmanAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClientFormCoordinator.getInstance().openPretragaAranzmanaFrm();
                } catch (Exception ex) {
                    Logger.getLogger(MainFrmController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        
        mainFrm.kreirajRezervacijuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClientFormCoordinator.getInstance().openRezervacijaFrm();
                } catch (Exception ex) {
                    Logger.getLogger(MainFrmController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        
        mainFrm.pretraziRezervacijuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClientFormCoordinator.getInstance().openPretragaRezervacijaFrm();
                } catch (Exception ex) {
                    Logger.getLogger(MainFrmController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        mainFrm.krajRadaBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Communication.getInstance().krajRada();
                    Zaposleni ulogovani = ClientFormCoordinator.getInstance().getUlogovani();
                    JOptionPane.showMessageDialog(mainFrm, "Doviđenja, " + ulogovani + ". Prijatan dan!");
                    System.exit(0);
                } catch (Exception ex) {
                    if (ex instanceof java.net.SocketException) {
                            JOptionPane.showMessageDialog(null, "Komunikacija izmedju servera je prekinuta");
                            mainFrm.dispose();
                            System.exit(0);
                        } else{
                                 Logger.getLogger(ClientFormCoordinator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        });
        
        mainFrm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Communication.getInstance().krajRada();
                    Zaposleni ulogovani = ClientFormCoordinator.getInstance().getUlogovani();
                    JOptionPane.showMessageDialog(mainFrm, "Doviđenja, " + ulogovani + ". Prijatan dan!");
                    System.exit(0);
                } catch (Exception ex) {
                   if (ex instanceof java.net.SocketException) {
                            JOptionPane.showMessageDialog(null, "Komunikacija izmedju servera je prekinuta");
                            mainFrm.dispose();
                            System.exit(0);
                        } else{
                                 Logger.getLogger(ClientFormCoordinator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    public MainFrm getMainFrm() {
        return mainFrm;
    }

   
    
}
