/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.*;

/**
 *
 * @author Maxwell Burner
 */
public class AppointmentCRUD extends CRUD {
    
    private ObservableList<Appointment> appointmentList;
    private static final String CREATE_STRING = "INSERT INTO appointments "+
                "(Title, Description, Type, Start, End, Customer_ID, User_ID, Contact_ID) "+
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String UPDATE_STRING = "UPDATE appointments SET "+
            "Title = ? "+
            "Description = ?"+
            "Location = ? "+
            "Type = ? "+
            "Start = ? "+
            "End = ? "+
            "Customer_ID = ? "+
            //"User_ID = ? "+
            "Contact_ID = ? "+
            "WHERE Appointment_ID = ?";
    
    private static final String DELETE_STRING = "DELETE FROM appointments "+
            "WHERE Appointment_ID = ?";
    
    /**Private constructor. Note that constructor initializes appointmentList but does 
     not populate it with entries. Used in static makeAppointmentCRUD method.
     @param conn The connection to the database.
     @param query The String containing the initial SELECT query. 
     @throws SQLException */
    private AppointmentCRUD(Connection conn, String query) throws SQLException{
        super(conn, query, CREATE_STRING, UPDATE_STRING, DELETE_STRING);
        
        appointmentList = FXCollections.observableArrayList();
        
    }
    
    /**Helper method to convert a localDateTime to a TimeStamp. 
     @param zone ZoneId for conversion.
     @param local LocalDateTime to be converted
     @return A timestamp.*/
    private static Timestamp localToTimestamp(ZoneId zone, LocalDateTime local){
        Instant hold = ZonedDateTime.of(local, zone).toInstant();
        return (new Timestamp(hold.getEpochSecond()));
    }
    
    /**Method to create a new appointment. Note that Appointment_ID is auto-incremented. 
     @param title String containing Title
     @param description String containing Description.
     @param location String containing location of appointment.
     @param type String indicating type of appointment. 
     @param start Start of appointment as LocalDateTime
     @param end End of appointment as LocalDateTime
     @param customer Customer participating in appointment.
     @param user User who created appointment. 
     @param contact Contact used to set up appointment?
     @throws SQLException */
    public void create(String title, String description, String location, String type,
            LocalDateTime start, LocalDateTime end, Customer customer, User user, Contact contact)
            throws SQLException {
        
        //HOW will I know which timezone to use for converting localdatetime to a long?
        
        ZoneId zHere = ZonedDateTime.now().getZone();
                
        createStatement.setString(1, title);
        createStatement.setString(2, description);
        createStatement.setString(3, location);
        createStatement.setString(4, type);
        
        createStatement.setTimestamp(5, localToTimestamp(zHere, start));
        createStatement.setTimestamp(6, localToTimestamp(zHere, end));
        
        createStatement.setInt(7, customer.getCustomerId());
        createStatement.setInt(8, user.getUserId());
        createStatement.setInt(9, contact.getContactId());
        
        createStatement.executeUpdate();
        createStatement.clearParameters();
        
        int nextId = appointmentList.get(appointmentList.size() - 1).getAppointmentId() + 1;
        appointmentList.add(new Appointment(nextId, title, description, location, type,
                start, end,
                customer, user, contact));
        
        
    }
    

    /**Method to retrieve data from the database. 
     @throws SQLException*/
    @Override
    public void retrieve() throws SQLException {
        ResultSet resultData = queryStatement.executeQuery(queryString);
        
        resultData.beforeFirst(); //Unnecessary?
        
        while(resultData.next()){
            appointmentList.add(new Appointment(resultData));
        }
        
    }
    
    

    /**Method to retrieve new data from the database. 
     @param query String containing the new SELECT query. 
     @throws SQLException*/
    @Override
    public void retrieve(String query) throws SQLException {
        this.queryString = query;
        retrieve();
    }
    
    
    /**Static factory(?) method to create and initialize an AppointmentCRUD instance.
     * @param conn The connection to the database.
     * @param query The SELECT query used to first get the appointment data.
     * @throws SQLException
     */
    public static AppointmentCRUD makeAppointmentCRUD(Connection conn, String query) throws SQLException{
        AppointmentCRUD out = new AppointmentCRUD(conn, query);
        out.retrieve();
        return out;
    }
    
    
    /**Method to update an existing appointment entry. Current version does not
     allow an appointment to be changed from one user to another, as this functionality is
     *not part of the software.
     @param id The id of the appointment to be updated.
     @param title The new value for Title
     @param description The new value for Description.
     @param location The new value for Location.
     @param type The new value for Type
     @param start The new value for Start
     @param end The new value for End
     @param customer The new customer the appointment will be with.
     @param user The user owning the appointment being modified.
     @param contact The new contact used for the appointment. 
     @throws SQLException */
    public void update(int id, String title, String description, String location, String type, 
            LocalDateTime start, LocalDateTime end, Customer customer, User user, Contact contact)
            throws SQLException{
        
        ZoneId zHere = ZonedDateTime.now().getZone();
                
        updateStatement.setString(1, title);
        updateStatement.setString(2, description);
        updateStatement.setString(3, location);
        updateStatement.setString(4, type);
        
        updateStatement.setTimestamp(5, localToTimestamp(zHere, start));
        updateStatement.setTimestamp(6, localToTimestamp(zHere, end));
        
        updateStatement.setInt(7, customer.getCustomerId());
        updateStatement.setInt(8, contact.getContactId());
        
        updateStatement.setInt(9, id);
        
        updateStatement.executeUpdate();
        updateStatement.clearParameters();
        

        
        for(Appointment a : appointmentList){
            if(a.getAppointmentId() == id){
                a.setTitle(title);
                a.setDescription(description);
                a.setLocation(location);
                a.setType(type);
                a.setStart(start);
                a.setEnd(end);
                a.setCustomerId(customer);
                //Note that user is not changed.
                a.setContactId(contact);
            }
        }
        
        

    }
    

    /**Method to delete a selected appointment entry. 
     @param id The Appointment_ID of the appointment to be deleted.
     @throws SQLException*/
    @Override
    public void delete(int id) throws SQLException {
        deleteStatement.setInt(1, id);
        deleteStatement.executeUpdate();
        deleteStatement.clearParameters();
        
        for(Appointment a : appointmentList){
            if(a.getAppointmentId() == id){
                appointmentList.remove(a);
            }
        }
    }

    /*@Override
    public void rollback() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    /*@Override
    public void commit() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    /**Debug method that prints appointments in appointmentList to console. */
    @Override
    public void printList() {
        System.out.println("/nAPPOINTMENT");
        for(Appointment a : appointmentList){
            a.print();
        }
    }


    
}
