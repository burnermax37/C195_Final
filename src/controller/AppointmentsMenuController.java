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
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class AppointmentsMenuController implements Initializable, Connected {
    Connection conn;
    Stage stage;
    Parent parent;

    @FXML
    private TextField searchTextField;

    @FXML
    private TableView<?> appointmentsTable;

    @FXML
    private Button appointmentDetailsButton;

    @FXML
    private Button modifyAppointmentButton;

    @FXML
    private Button deleteAppointmentButton;

    @FXML
    private Button lastUnitButton;

    @FXML
    private Button nextUnitButton;
    
    @FXML
    private Button appointmentReportsButton;

    @FXML
    private RadioButton byWeekRadioButton;

    @FXML
    private ToggleGroup displayUnits;

    @FXML
    private RadioButton byMonthRadioButton;
    
    /**Clicking "Count Report" button goes to appointment report display. */
    @FXML
    void onActionDisplayAppoinmentsReport(ActionEvent event) {

    }

    @FXML
    void onActionDisplayByMonth(ActionEvent event) {

    }

    @FXML
    void onActionDisplayByWeek(ActionEvent event) {

    }

    @FXML
    void onActionLastUnit(ActionEvent event) {

    }

    @FXML
    void onActionNextUnit(ActionEvent event) {

    }

    @FXML
    void onActionSearch(ActionEvent event) {

    }

    @FXML
    void onActionShowAppointmentDetails(ActionEvent event) {

    }

    @FXML
    void onActionShowCreateAppointment(ActionEvent event) {

    }

    @FXML
    void onActionShowDeleteAppointment(ActionEvent event) {

    }

    @FXML
    void onActionShowMainMenu(ActionEvent event) throws IOException {
        Button sourceRef = (Button) event.getSource();
        
        stage = (Stage) sourceRef.getScene().getWindow();
        
        URL file = getClass().getResource("/view/MainForm.fxml");
        
        parent = FXMLLoader.load(file);
        
        stage.setScene(new Scene(parent));
        stage.show();

    }

    @FXML
    void onActionShowModifyAppoinment(ActionEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void receiveConnection(Connection conn) {
        this.conn = conn;
    }

}


