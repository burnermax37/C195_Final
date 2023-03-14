/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class to represent contacts for Appointments. 
 * @author Maxwell Burner
 */
public class Contact {
    private final int contactId;
    private String contactName;
    private String email;

    /**Unused constructor for contact not already in database.
     @param contactId Integer to become contact Id.
     @param contactName String to become contact name.
     @param email String to become contact email address.*/
    /*public Contact(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }*/
    
    /** Method to create contact that already exists in database.Internal pointer
     of passed resultSet must already be pointing to row with contact to be instaniated
     as an object instance.
     @param contactData ResultSet containing data on contacts.
     * @throws java.sql.SQLException*/
    public Contact(ResultSet contactData) throws SQLException{
        this.contactId = contactData.getInt("Contact_ID");
        this.contactName = contactData.getString("Contact_Name");
        this.email = contactData.getString("Email");
    }

    /**Getter for contact ID.
     @return Contact ID as integer.*/
    public int getContactId() {
        return contactId;
    }

    /**Getter for contact name. 
     @return Contact name as String.*/
    public String getContactName() {
        return contactName;
    }

    /**Getter for contact email address.
     @return Contact email address as String. */
    public String getEmail() {
        return email;
    }

    /**Unused setter for contact name. 
     @param contactName String to become new contact name. */
    /*public void setContactName(String contactName) {
        this.contactName = contactName;
    }*/

    /**Unused setter for contact email.
     @param email String to become new contact email.*/
    /*public void setEmail(String email) {
        this.email = email;
    }*/
    
    /**Debug function to print contact information to console. */
    public void print(){
        String base = "Contact_ID: %d,\t"+
                "Contact_Name: %s,\t"+
                "Email: %s";
        
        System.out.println(String.format(base, contactId, contactName, email));
    }
    
    

    
    
}
