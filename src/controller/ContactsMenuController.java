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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Contact;
import model.DataProvider;
/**
 * Controller class for Contacts Menu. This menu provides a list of all contacts, 
 * and allows user to generate a report showing all appointments for a given contact.
 * @author Maxwell Burner
 */


public class ContactsMenuController implements Initializable, Connected {
    Stage stage;
    Parent parent;
    Connection conn;

    @FXML
    private TableView<Contact> contactsTable;
    
    @FXML
    private TableColumn<Contact, Integer> contactIdColumn;

    @FXML
    private TableColumn<Contact, String> contactNameColumn;

    @FXML
    private TableColumn<Contact, String> contactEmailColumn;

    @FXML
    private Button contactScheduleButton;

    @FXML
    void onActionDisplayMain(ActionEvent event) throws IOException {
        Button sourceRef = (Button) event.getSource();
        
        stage = (Stage) sourceRef.getScene().getWindow();
        
        URL file = getClass().getResource("/view/MainForm.fxml");
        
        parent = FXMLLoader.load(file);
        
        stage.setScene(new Scene(parent));
        stage.show();

    }

    @FXML
    void onActionViewSchedule(ActionEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try{
            contactsTable.setItems(DataProvider.getCONTACTS());
        
            contactIdColumn.setCellValueFactory(new PropertyValueFactory("contactId"));
            contactNameColumn.setCellValueFactory(new PropertyValueFactory("contactName"));
            contactEmailColumn.setCellValueFactory(new PropertyValueFactory("email"));
        }catch(Exception e){
            System.out.println("**************FAILTOLOADCONTACTS####################");
        }
        
        
    }

    @Override
    public void receiveConnection(Connection conn) {
        this.conn = conn;
    }

}
