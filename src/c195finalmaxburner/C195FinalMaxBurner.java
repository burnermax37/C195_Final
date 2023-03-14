/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195finalmaxburner;

import controller.*;
import controller.MainController;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

/**
 *
 * @author Maxwell Burner
 */
public class C195FinalMaxBurner extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        System.out.println("##############DEBUG: Start is running################3");

   
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/LoginForm.fxml"));
        loader.load();
        
        LoginController lController = loader.getController();
        /*if(JDBC.connection == null){
            System.out.println("########################DEBUG In Start Connection is Null######################333");
        }else{
            System.out.println("################################DEBUG In Start connection is "+JDBC.connection.toString()+" #########################33");
        }*/
        
        
        
        
        /*tController.receiveConnection(JDBC.connection);*/
        
        
        Parent root = loader.getRoot();
        
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        

        
        
        launch(args);
        
    }
    
}
