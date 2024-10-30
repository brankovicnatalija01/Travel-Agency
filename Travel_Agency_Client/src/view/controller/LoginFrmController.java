/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.controller;


import communication.Communication;
import domain.Korisnik;
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
import view.form.LoginFrm;

/**
 *
 * @author natalija
 */
public class LoginFrmController {
    private final LoginFrm loginFrm;

    public LoginFrmController(LoginFrm loginFrm) {
        this.loginFrm = loginFrm;
        addActionListener();
    }

    public void openForm() {
        loginFrm.setVisible(true);
        loginFrm.setTitle("Prijava na sistem");
    }

    private void addActionListener() {
        
        loginFrm.loginBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                login(actionEvent);
            }

            private void login(ActionEvent actionEvent) {
                try {
                  
                    String username = loginFrm.getjTextFieldUsername().getText().trim();
                    String password = String.copyValueOf(loginFrm.getjPasswordField1().getPassword()).trim();
                    
                    validateForm(username, password);
                    
                    Zaposleni ulogovani = Communication.getInstance().login(username, password);

                    if(ulogovani == null) {
                        String poruka = Communication.getInstance().getPorukaLogin();
                        JOptionPane.showMessageDialog(loginFrm, poruka, "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {  
                        ClientFormCoordinator.getInstance().setUlogovani(ulogovani);
                        JOptionPane.showMessageDialog(loginFrm, "Uspešna prijava", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        ClientFormCoordinator.getInstance().openMainFrm();
                        loginFrm.dispose();
                        
                    } } catch (Exception ex) {
                        
                        if (ex instanceof java.net.SocketException) {
                            JOptionPane.showMessageDialog(null, "Komunikacija izmedju servera je prekinuta");
                            loginFrm.dispose();
                            System.exit(0);
                        } else {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                            ex.printStackTrace();
                            }
                }
                
                resetForm();
            }

            private void resetForm() {
                loginFrm.getjTextFieldUsername().setText("");
                loginFrm.getjPasswordField1().setText("");
            }

            private void validateForm(String username, String password) throws Exception {

                if (username.isEmpty()) {
                   JOptionPane.showMessageDialog(loginFrm, "Polje korisničko ime ne sme biti prazno!", "Greška", JOptionPane.ERROR_MESSAGE);
                   return;
                }
                
                if (password.isEmpty()) {
                   JOptionPane.showMessageDialog(loginFrm, "Polje lozinka ne sme biti prazno!", "Greška", JOptionPane.ERROR_MESSAGE);
                   return;
                }
            }
        });
        
      loginFrm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Communication.getInstance().krajRadaLogin();       
                } catch (Exception ex) {
                    Logger.getLogger(LoginFrmController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }          
        });
       

    }
    
}
