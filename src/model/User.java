/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class to represent users of appointment management system.
 * @author Maxwell Burner
 */
public class User {
    private final int userId;
    private String userName;
    private String password;

    /**Unused constructor for a user not already in database.
    @param userId integer to become user ID
    @param userName String to become user name.
    @parm password String to become password*/
    /*public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }*/
    
    /** Constructor for user already in the database.Internal pointer of passed
    resultset must already be pointing to row corresponding to user to be converted into
    an object instance. 
    @param userData Resultset with data on users.
     * @throws java.sql.SQLException*/
    public User(ResultSet userData) throws SQLException{
        this.userId = userData.getInt("User_ID");
        this.userName = userData.getString("User_Name");
        this.password = userData.getString("Password");
    }

    /**Getter for user id.
     @return User Id as integer.*/
    public int getUserId() {
        return userId;
    }

    /**Getter for user name. 
     @return User name as String.*/
    public String getUserName() {
        return userName;
    }

    /**Getter for password.
     @return User password as String.*/
    public String getPassword() {
        return password;
    }

    /**Unused setter for user name. 
    @param userName String to become new user name.*/
    /*public void setUserName(String userName) {
        this.userName = userName;
    }*/

    /**Unused setter for password. 
     @param password String to become new password.*/
    /*public void setPassword(String password) {
        this.password = password;
    }*/
    
    
    /**Debug function to print user info to console. */
    public void print(){
        String base = "User_ID: %d,\t"+
                "User_Name: %s,\t"+
                "Password: %s";
        System.out.println(String.format(base, userId, userName, password));
    }
        
}
