/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author natalija
 */
public class Receiver implements Serializable{
     private Socket socket;

    public Receiver(Socket socket) {
        this.socket = socket;
    }
    
    public Object receive() throws Exception{
         try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            return in.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Komunikacija izmedju servera je prekinuta");
            System.exit(0);
            throw new Exception("Error receiving object!\n"+ex.getMessage());
        }
    }
    
}
