/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.coordinator;

import view.controller.KonfBazaController;
import view.controller.ServerFrmController;
import view.form.KonfBazaFrm;
import view.form.ServerFrm;

/**
 *
 * @author natalija
 */
public class ServerFormCoordinator {
    private static ServerFormCoordinator instance;
    private final ServerFrmController serverFormController;

    public ServerFormCoordinator() {
        serverFormController = new ServerFrmController(new ServerFrm());
    }
    
    public static ServerFormCoordinator getInstance(){
        if(instance == null){
            instance = new ServerFormCoordinator();
        }
        return instance;
    }
    
    public void openMainForm(){
        serverFormController.openForm();
    }
    
    public void openFormaKonfBaza() {
        KonfBazaController konfBazaController = new KonfBazaController(new KonfBazaFrm(serverFormController.getServerFrm(), true));
        konfBazaController.openForm();
    }

    public ServerFrmController getServerFormController() {
        return serverFormController;
    }

    
    
}
