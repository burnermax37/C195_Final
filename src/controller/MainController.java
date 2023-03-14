/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import c195finalmaxburner.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML controller class for main menu. 
 * @author Maxwell Burner
 */
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainController implements Connected{
    Connection conn;
    Parent parent;
    Stage stage;

    @FXML
    private Label welcomeLabel;

    @FXML
    void onActionLoadAppointmentsMenu(ActionEvent event) throws IOException {
        Button sourceRef = (Button) event.getSource();
        
        FXMLLoader loader = new FXMLLoader();
        URL file = getClass().getResource("/view/AppointmentsMenu.fxml");
        loader.setLocation(file);
        loader.load();
        
        AppointmentsMenuController amController = loader.getController();
        amController.receiveConnection(conn);
        
        
        
        stage = (Stage) sourceRef.getScene().getWindow();
        

        
        parent = loader.getRoot();
        
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    void onActionLoadCustomersMenu(ActionEvent event) throws IOException {
        Button sourceRef = (Button) event.getSource();
        
        FXMLLoader loader = new FXMLLoader();
        URL file = getClass().getResource("/view/CustomerMenu.fxml");
        loader.setLocation(file);
        loader.load();
        
        CusotmerMenuController cmController = loader.getController();
        cmController.receiveConnection(conn);
        
        
        
        stage = (Stage) sourceRef.getScene().getWindow();
        

        
        parent = loader.getRoot();
        
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    void onActionLoadContactsMenu(ActionEvent event) throws IOException {
        Button sourceRef = (Button) event.getSource();
        
        FXMLLoader loader = new FXMLLoader();
        URL file = getClass().getResource("/view/ContactsMenu.fxml");
        loader.setLocation(file);
        loader.load();
        
        ContactsMenuController cmController = loader.getController();
        cmController.receiveConnection(conn);
        
        
        
        stage = (Stage) sourceRef.getScene().getWindow();
        

        
        parent = loader.getRoot();
        
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @Override
    public void receiveConnection(Connection conn) {
        this.conn = conn;
    }



}
