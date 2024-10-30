/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.Controller;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import server.thread.ProcessClientsRequests;

/**
 *
 * @author natalija
 */
public class Server extends Thread{
    
    private List<ProcessClientsRequests> klijenti;
    private ServerSocket ss;
    private boolean kraj = false;

    public Server() {
        klijenti = new LinkedList<>();
        try {
            ss = new ServerSocket(9000);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Server je već pokrenut");
            System.exit(0);
            
        }
    }

    @Override
    public void run() {
        while(!kraj){
            try {
                System.out.println("Čekanje na konekciju");
                Socket socket = ss.accept();
               
                ProcessClientsRequests klijent = new ProcessClientsRequests(socket,this);
                System.out.println("Klijent se povezao");
                klijent.start();
                klijenti.add(klijent);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     public List<ProcessClientsRequests> getKlijenti() {
        return klijenti;
    }
    
     public void kraj() throws Exception{
          try {
            for (ProcessClientsRequests processClientRequests : klijenti) {
                processClientRequests.kraj();
            }
            
            Controller.getInstance().setPrijavljeniZaposleni(new ArrayList<>());
            ss.close();
            kraj = true;
   
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    public boolean isKraj() {
        return kraj;
    }

    
    
}
