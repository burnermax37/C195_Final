/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.*;

public class TestController implements Initializable, Connected {
    
    Stage stage;
    Parent parent;
    Connection conn;

    @FXML
    private Label testLabel;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        JDBC.openConnection();
        
        
        
        if(JDBC.connection == null){
            System.out.println("#########################DEBUG in TestController Connection is Null ###########################3");
        }else{
            System.out.println("############################DEBUG in TestController Connection is "+conn.toString()+ " ############################");
        }
        JDBC.closeConnection();
    }

    @Override
    public void receiveConnection(Connection conn) {
        this.conn = conn;
    }

}
