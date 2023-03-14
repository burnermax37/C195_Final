/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class to store static data on users, first level divisions, and contacts.
 * Serves to provide relational integrity in updates made using program rather than within 
 * database. Also provides user validation. In short, handles all the data tables for
 * which access is needed, but not full CRUD capability.
 * @author Maxwell Burner
 */
public class DataProvider {
    private static final String CONTACT_QUERY = "SELECT * FROM contacts";
    private static final String DIVISION_QUERY = "SELECT * FROM first_level_divisions";
    private static final String USER_QUERY = "SELECT * FROM  users";
    
    
    private static final ObservableList<Contact> CONTACTS = FXCollections.observableArrayList();
    private static final ObservableList<User> USERS = FXCollections.observableArrayList();
    private static final ObservableList<FirstLevelDivision> DIVISIONS = FXCollections.observableArrayList();
    
    /** * Method that populates static lists.Must be called before any other static
     methods of this class are called.
     * @param conn Connection object
     * @throws java.sql.SQLException*/
    public static void initialize(Connection conn) throws SQLException{
        Statement stmt = conn.createStatement();
        
        ResultSet resultData;
        

        resultData = stmt.executeQuery(USER_QUERY);
        while(resultData.next()){
            USERS.add(new User(resultData));
        }
        
        resultData = stmt.executeQuery(CONTACT_QUERY);
        while(resultData.next()){
            CONTACTS.add(new Contact(resultData));
        }
        
        resultData = stmt.executeQuery(DIVISION_QUERY);
        while(resultData.next()){
            DIVISIONS.add(new FirstLevelDivision(resultData));
        }
    }

    /**Getter for contacts. 
     @return CONTACTS observableList.*/
    public static ObservableList<Contact> getCONTACTS() {
        return CONTACTS;
    }

    /**Getter for users. 
     @return USERS observableList.*/
    public static ObservableList<User> getUSERS() {
        return USERS;
    }

    /**Getter for divisions.
     @return DIVISION observableList.*/
    public static ObservableList<FirstLevelDivision> getDIVISIONS() {
        return DIVISIONS;
    }
    
    
    
    
    /**Debug method to print users to console. */
    public static void printUsers(){
        System.out.println("USERS");
        for(User u : USERS){
            u.print();
        }
    }
    
    /**Debug method to print contacts to console. */
    public static void printContacts(){
        System.out.println("CONTACTS");
        for(Contact c : CONTACTS){
            c.print();
        }
    }
    
    /**Debug method to print first level divisions to console. */
    public static void printDivisions(){
        System.out.println("DIVISIONS");
        for(FirstLevelDivision d : DIVISIONS){
            d.print();
        }
    }
    
    /**Method to check if a user with a particular ID is in USERS.
     @param userId User ID being search for.
     * @return True if user with provided ID is in USERS; false otherwise. */
    public static boolean checkForUser(int userId){
        for(User u : USERS){
            if(u.getUserId() == userId){
                return true;
            }
        }
        return false;
    }
    
     /**Method to check if a division with a particular ID is in DIVISIONS. 
     @param divisionId Division ID being search for.
     * @return True if division with provided ID is in DIVISIONS; false otherwise.*/
    public static boolean checkForDivision(int divisionId){
        for(FirstLevelDivision d : DIVISIONS){
            if(d.getDivisionId() == divisionId){
                return true;
            }
        }
        return false;
    }
    
    /**Method to check if a contact with a particular ID is in CONTACTS.
     @param contactId User ID being search for.
     * @return True if a contact with the provided ID is found, false otherwise.*/
    public static boolean checkForContact(int contactId){
        for(Contact c : CONTACTS){
            if(c.getContactId() == contactId){
                return true;
            }
        }
        return false;
    }
    
    
    /**Method to retrieve a User from USERS by ID.
     * @param searchId Id of user to retrieve.
     * @return User with matching ID; null if no such user is found in USERS */
    public static User getUserById(int searchId){
        for(User u : USERS){
            if(u.getUserId() == searchId){
                return u;
            }
        }
        return null;
    }
    
    
     /**Method to retrieve a Contact from CONTACTS by ID.
     * @param searchId Id of contact to retrieve.
     * @return Contact with matching ID; null if no such contact is found in CONTACTS */
    public static Contact getContactById(int searchId){
        for(Contact c : CONTACTS){
            if(c.getContactId() == searchId){
                return c;
            }
        }
        return null;
    }
    
    /**Method to retrieve a FirstLevelDivision from DIVISIONS by ID.
    * @param searchId Id of FirstLevelDivision to retrieve.
    * @return FirstLevelDivision with matching ID; null if no such FirstLevelDivision is found in DIVISIONS */
    public static FirstLevelDivision getDivisionById(int searchId){
        for(FirstLevelDivision d : DIVISIONS){
            if(d.getDivisionId() == searchId){
                return d;
            }
        }
        return null;
    }
    
    
}
