/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import server.Settings;
import view.form.KonfBazaFrm;

/**
 *
 * @author natalija
 */
public class KonfBazaController {
    
    private final KonfBazaFrm konfBazaFrm;

    public KonfBazaController(KonfBazaFrm konfBazaFrm) {
        this.konfBazaFrm = konfBazaFrm;
        addActionListener();
    }


    private void addActionListener() {
      
        konfBazaFrm.addSacuvajBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String url = konfBazaFrm.getjTextFieldURL().getText().trim();
                String username = konfBazaFrm.getjTextFieldUsername().getText().trim();
                String password = konfBazaFrm.getjTextFieldPassword().getText().trim();
                
                if(url.isEmpty()|| username.isEmpty()){
                    JOptionPane.showMessageDialog(konfBazaFrm, "URL i username moraju biti uneti");
                    return;
                }
                
                if(!(url.contains("jdbc:mysql://"))){
                    JOptionPane.showMessageDialog(konfBazaFrm, "Niste dobro uneli URL. Pokušajte ponovo");
                    return;
                }
                
                try {
                    Settings.getInstance().setURL(url);
                    Settings.getInstance().setUsername(username);
                    Settings.getInstance().setPassword(password);
                    Settings.getInstance().sacuvaj();
                
                    JOptionPane.showMessageDialog(konfBazaFrm, "Parametri su sačuvani");
                    konfBazaFrm.dispose();
                
                 } catch(Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(konfBazaFrm, "Greška, parametri nisu sačuvani", "Greška", JOptionPane.ERROR_MESSAGE);
                     }
            }
        });
    }
    
    public void openForm() {
        konfBazaFrm.setTitle("Podešavanja baze");
        konfBazaFrm.setVisible(true);
    }
    
}
