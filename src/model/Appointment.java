/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.Instant;

/**
 * Class to represent scheduled appointments.
 * @author Maxwell Burner
 */
public class Appointment {
    private final int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    //private ZoneOffset timezone;
    private LocalDateTime start;
    private LocalDateTime end;
    
    private int customerId;
    private int userId;
    private int contactId;

    /**Constructor for appointments not already in the database. 
     @param appointmentId Integer to become appointment ID.
     @param title Integer to become title.
     @param description String to become description. 
     @param location String to become location.
     @param type String to become type.
     @param start Starting time as LocalDateTime.
     @param end Ending time as LocalDateTime.
     @param customer Customer associated with appointment. 
     @param user User to whom appointment belongs.
     @param contact Contact associated with appointment.*/
    public Appointment(int appointmentId, String title, String description, String location,
            String type, /*ZoneOffset timezone,*/ LocalDateTime start, LocalDateTime end,
            Customer customer, User user, Contact contact) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        //this.timezone = timezone;
        this.start = start;
        this.end = end;
        this.userId = user.getUserId();
        this.customerId = customer.getCustomerId();
        this.contactId = contact.getContactId();
    }
    
    /**Helper method to convert timestamp to LocalDateTime. Needed because there
     * is no way to directly get a LocalDateTime object from a ResultSet.
     * @param timestamp Initial timestamp
     * @return A LocalDateTime instance
     */
    private LocalDateTime stampToLocal(Timestamp timestamp){
        Instant instant = Instant.ofEpochMilli(timestamp.getTime());
        LocalDateTime out = LocalDateTime.from(instant);
        return out;
    }
    
    
    
    /**Constructor for appointments already present in the database. Internal pointer
     * of passed resultset must already be pointing at row to be instantiated.
     @param appointmentData 
     @throws SQLException*/
    public Appointment(ResultSet appointmentData) throws SQLException{
        this.appointmentId = appointmentData.getInt("Appointment_ID");
        this.title = appointmentData.getString("Title");
        this.description = appointmentData.getString("Description");
        this.location = appointmentData.getString("Location");
        this.type = appointmentData.getString("Type");
        
        this.start =  stampToLocal(appointmentData.getTimestamp("Start"));
        this.end = stampToLocal(appointmentData.getTimestamp("End"));
        
        this.customerId = appointmentData.getInt("Customer_ID");
        this.userId = appointmentData.getInt("User_ID");
        this.contactId = appointmentData.getInt("Contact_ID");
        
    }

    /**Getter from appointment ID.
     @return appointment ID as an integer.*/
    public int getAppointmentId() {
        return appointmentId;
    }

    /**Getter for title.
     @return title as String*/
    public String getTitle() {
        return title;
    }

    /**Getter for description.
     @return Description as String.*/
    public String getDescription() {
        return description;
    }
    
    /**Getter for location.
     @return Location as String.*/
    public String getLocation() {
        return location;
    }

    /**Getter for type.
     @return Type as String.*/
    public String getType() {
        return type;
    }

    /**Getter for start time.
     @return Start as LocalDateTime*/
    public LocalDateTime getStart() {
        return start;
    }

    /**Getter for end time.
     @return End as LocalDateTime.*/
    public LocalDateTime getEnd() {
        return end;
    }

    /**Getter for Customer ID.
     @return Customer ID as integer.*/
    public int getCustomerId() {
        return customerId;
    }

    /**Getter for user ID.
     @return User ID as integer.*/
    public int getUserId() {
        return userId;
    }
    

    /**Getter for contact ID.
     @return Contact ID as integer.*/
    public int getContactId() {
        return contactId;
    }

    /**Setter for title.
     @param title String to become title.*/
    public void setTitle(String title) {
        this.title = title;
    }

    /**Setter for description.
     @param description String to become new description.*/
    public void setDescription(String description) {
        this.description = description;
    }

    /**Setter for location.
     @param location String to become location.*/
    public void setLocation(String location) {
        this.location = location;
    }

    /**Setter for type. 
     @param Type string to become type.*/
    public void setType(String type) {
        this.type = type;
    }

    /**Setter for Start.
     @param start LocalDateTime to become start time.*/
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**Setter for end.
     @param end LocalDateTime to become end time.*/
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**"Setter" for customer ID. Customer ID of passed customer is assigned to 
     customerId parameter of this instance; this ensure no appointment can be given a 
     customer ID for a customer that does not exist.
     @param customer Customer to be associated with appointment.*/
    public void setCustomerId(Customer customer) {
        this.customerId = customer.getCustomerId();
    }

    /**Unused "Setter" for user ID. User ID of passed user is assigned to 
     userId parameter of this instance; this ensure no appointment can be given a 
     user ID for a user that does not exist. Unused because appointments cannot be
     * transferred from one user to another.
     @param user The user to be associated to this appointment.*/
    /*public void setUserId(User user) {
        this.userId = user.getUserId();
    }*/

    /**"Setter" for contact ID. Contact ID of passed contact is assigned to 
     contactID parameter of this instance; this ensures no appointment can be given
     a contact ID for contact that does not exist.
     @param contact The contact to which this appointment is to be associated.*/
    public void setContactId(Contact contact) {
        this.contactId = contact.getContactId();
    }
    
    
    /**Debug function to print appointment information to console. */
    public void print(){
        String base = "Appointment_ID: %d,\t"+
                "Title: %s,\t"+
                "Description: %s,\t"+
                "Location: %s,\t"+
                "Start: %s,\t"+
                "End: %s,\t"+
                "Customer_ID: %d,\t"+
                "User_ID: %d,\t"+
                "Contact_ID: %d";
        
        System.out.println(String.format(base, appointmentId, title, description,
                location, start.toString(), end.toString(), customerId, userId, contactId));
    }

    
    
}
