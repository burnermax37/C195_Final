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
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

/**
 *
 * @author Maxwell Burner
 */
public class LoginController implements Initializable, Connected {
    Connection conn;
    Parent parent;
    Stage stage;
    
    

    @FXML
    private TextField userNameField;

    @FXML
    private TextField passowrdField;

    @FXML
    void onActionSubmitCredentials(ActionEvent event) throws IOException {
        Button sourceRef = (Button) event.getSource();
        
        FXMLLoader loader = new FXMLLoader();
        URL file = getClass().getResource("/view/MainForm.fxml");
        loader.setLocation(file);
        
        loader.load();
        
        MainController mController = loader.getController();
        mController.receiveConnection(conn);
        
        
        
        stage = (Stage) sourceRef.getScene().getWindow();
        
        parent = loader.getRoot();
        
        stage.setScene(new Scene(parent));
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
    }   
    
    /**Method that loads main menu if user credentials are accepted. */
    private void loadMain(ActionEvent event) throws IOException{

    }

    @Override
    public void receiveConnection(Connection conn) {
        this.conn = conn;
    }
    
}
