/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 * Abstract class for CRUD classes; declares functions for CRUD operations.
 * @author Maxwell Burner
 */
public abstract class CRUD {
    
    protected Connection conn;
    protected String queryString;
    protected Statement queryStatement;
    protected final PreparedStatement createStatement;
    protected final PreparedStatement updateStatement;
    protected final PreparedStatement deleteStatement;

    
    /**Constructor. 
     @param conn Connection object used to create the CRUD instance.
     @param queryString The String containing the first query to get the data.
     @param createString The string to create the prepared statement for creating new entities.
     @param updateString The string to create the prepared statement for updating entities.
     @param deleteString The string to create the prepared statement for deleting entities.
     * @throws java.sql.SQLException*/
    public CRUD(Connection conn, String queryString,
            String createString, String updateString,String deleteString) throws SQLException{
        System.out.println("#############################DEBUG CRUD Constructor has started###############33");
        this.queryString = queryString;
        System.out.println("####################################DEBUG: "+this.queryString+" #####################################");
        
        this.conn = conn;
        
        System.out.println("###########################DEBUG CONN: "+this.conn.toString()+" ############################");
        
        this.queryStatement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

        
        this.createStatement = conn.prepareStatement(createString);
        this.updateStatement = conn.prepareStatement(updateString);
        this.deleteStatement = conn.prepareStatement(deleteString);
    }
    
    
    /**Method to retrieve data using stored query.
     * @throws java.sql.SQLException */
    public abstract void retrieve() throws SQLException;
    
    /**Method to retrieve data using new query. 
     @param query The new query.
     * @throws java.sql.SQLException*/
    public abstract void retrieve(String query) throws SQLException;
   
    
    /**Method to delete a data entry.
     @param id The primary key value of the entry to be deleted.
     * @throws java.sql.SQLException*/
    public abstract void delete(int id) throws SQLException;
    
    
    //Commit and rollback are disabled. Commits are automatic.
    /**Method to pass changes to data on to database.
     * @throws java.sql.SQLException */
    /*public abstract void rollback() throws SQLException;*/
    
    /**Method to undo changes to data.
     * @throws java.sql.SQLException */
    /*public abstract void commit() throws SQLException;*/
    
    
    
    
    /**Debug method to print current data.*/
    public abstract void printList();


    /**Setter for queryString. Used to change what data is being retrieved; this
     * method should almost always be followed by a call to the retrieve method.
     @param queryString New query as string.*/
    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
    
    
    
    /**Getter for queryStatement. 
     @return QueryStatement, statement object used to get results.*/
    public Statement getQueryStatement() {
        return queryStatement;
    }




    
}
