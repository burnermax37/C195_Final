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
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;
import model.CustomerCRUD;
import model.JDBC;

/**
 *
 * @author Maxwell Burner
 */
public class CusotmerMenuController implements Initializable, Connected {
    Connection conn;
    Stage stage;
    Parent parent;
    
    private static CustomerCRUD customerData;

    @FXML
    private TextField searchTextField;

    @FXML
    private TableView<Customer> customersTable;
   
    @FXML
    private TableColumn<Customer, Integer> customerIdColumn;

    @FXML
    private TableColumn<Customer, String> customerNameColumn;

    @FXML
    private TableColumn<Customer, String> customerAddressColumn;

    @FXML
    private TableColumn<Customer, String> customerPOColumn;

    @FXML
    private TableColumn<Customer, String> customerPhoneColumn;

    @FXML
    private Button customerDetailsButton;

    @FXML
    private Button modifyCustomerButton;

    @FXML
    private Button deleteCustomerButton;
    

    @FXML
    void onActionCreateCustomer(ActionEvent event) {

    }

    @FXML
    void onActionDeleteCustomer(ActionEvent event) {

    }

    @FXML
    void onActionDisplayCustomerDetails(ActionEvent event) {

    }

    @FXML
    void onActionDisplayMainMenu(ActionEvent event) throws IOException {
        Button sourceRef = (Button) event.getSource();
        
        stage = (Stage) sourceRef.getScene().getWindow();
        
        URL file = getClass().getResource("/view/MainForm.fxml");
        
        parent = FXMLLoader.load(file);
        
        stage.setScene(new Scene(parent));
        stage.show();

    }

    @FXML
    void onActionDisplayModifyCustomer(ActionEvent event) {

    }

    @FXML
    void onActionSearchCustomers(ActionEvent event) {

    }

    @Override
    /**Initialization. A CustomerCRUD is generated that loads data on all customers;
     this provides the data for the table view.*/
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            System.out.println("###################################DEBUG: CustomerMenuController.initialize has begun ######################");
            if(conn == null){
                System.out.println("###############################################Connection is Null################################3");
            }
            
            //Get data

            customerData = CustomerCRUD.makeCustomerCRUD(conn, "SELECT * FROM customers");
            
            //Load table
            customersTable.setItems(customerData.getCustomerList());
            
            //Load columns
            customerIdColumn.setCellValueFactory(new PropertyValueFactory("customerId"));
            customerNameColumn.setCellValueFactory(new PropertyValueFactory("customerName"));
            customerAddressColumn.setCellValueFactory(new PropertyValueFactory("address"));
            customerPOColumn.setCellValueFactory(new PropertyValueFactory("postalCode"));
            customerPhoneColumn.setCellValueFactory(new PropertyValueFactory("phone"));
            
            //TODO: add column that shows first level division
            JDBC.closeConnection();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CusotmerMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void receiveConnection(Connection conn) {
        this.conn = conn;
    }

}
