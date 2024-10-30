/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.controller;

import controller.Controller;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import server.Server;
import view.coordinator.ServerFormCoordinator;
import view.form.ServerFrm;

/**
 *
 * @author natalija
 */
public class ServerFrmController {
  private final ServerFrm serverFrm;


    public ServerFrmController(ServerFrm serverForm) {
        this.serverFrm = serverForm;
        addActionListener();
    }

    public void openForm() {
        serverFrm.setVisible(true);
    }

    private void addActionListener() {
        
        serverFrm.addBazaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServerFormCoordinator.getInstance().openFormaKonfBaza();
            }
        });

        serverFrm.addOAutoruActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(serverFrm, "Softver je napravila studentkinja Natalija Branković 2020/0065.");
            }
        });

        serverFrm.addPokreniServerBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
              
                   boolean uspesno = Controller.getInstance().startujServer();

                   if(uspesno) {
                        serverFrm.getjButtonPokreniServer().setEnabled(false);
                        serverFrm.getjButtonZaustaviServer().setEnabled(true);
                        serverFrm.getjLabelStatus().setText("Server je pokrenut");
                        serverFrm.getjLabelStatus().setForeground(Color.GREEN);
       
                } else {
                    JOptionPane.showMessageDialog(null, "Server je već pokrenut");
                    serverFrm.dispose();
                }        
            }
        });

        serverFrm.addZaustaviServerActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Controller.getInstance().stopServer();
                    
                    serverFrm.getjButtonPokreniServer().setEnabled(true);
                    serverFrm.getjButtonZaustaviServer().setEnabled(false);
                    
                    serverFrm.getjLabelStatus().setText("Server je zaustavljen");
                    serverFrm.getjLabelStatus().setForeground(Color.RED);
                    JOptionPane.showMessageDialog(serverFrm, "Gašenje programa...");
                    serverFrm.dispose();
                    System.exit(0);

                } catch (Exception ex) {
                    Logger.getLogger(ServerFrmController.class.getName()).log(Level.SEVERE, null, ex);
                }
}
        });

    }

   public ServerFrm getServerFrm() {
        return serverFrm;
    }

   
}

   
