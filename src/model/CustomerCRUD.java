/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class providing Create/Retrieve/Update/Delete tools for customer data. 
 * Class stores retrieved data as ObservableList of customer objects, and stores
 * the query used to retrieve data. Class provides prepared statements for use in 
 * creating, updating, and deleting customers. Ultimate purpose is to ensure that 
 * data displayed in tableviews is coordinated with data in database.
 * 
 * Note: Current version assumes updates are automatic, and thus does not truly
 * implement commit and rollback methods. 
 * @author Maxwell Burner
 */
public class CustomerCRUD extends CRUD {
    
    private ObservableList<Customer> customerList;
    private static final String CREATE_STRING = "INSERT INTO customers "+
                "(Customer_Name, Address, Postal_Code, Phone, Division_ID) "+
                "VALUES (?, ?, ?, ?, ?)";
    
    private static final String UPDATE_STRING = "UPDATE customers SET "+
            "Customer_Name = ? "+
            "Address = ? "+
            "Postal_Code = ? "+
            "Phone = ? "+
            "Division_ID = ? "+
            "WHERE Customer_ID = ?" ;

    /**Getter for customer data list. 
     @return Customer Data as ObservableList*/
    public ObservableList<Customer> getCustomerList() {
        return customerList;
    }
    private static final String DELETE_STRING = "DELETE FROM customers "+
            "WHERE Customer_ID = ?";
            
    
    
    /**Private constructor. Note that after the constructor has run the customerList
     * will be initialized, but not yet have any entries. Used in static method
     * makeCustomerCRUD.
     @param conn The connection to the database.
     @param query The string with the initial query to get customer data.
     * @throws java.sql.SQLException*/
    private CustomerCRUD(Connection conn, String query) throws SQLException{
        super(conn, query, CREATE_STRING, UPDATE_STRING, DELETE_STRING);
        
        customerList = FXCollections.observableArrayList();

    }
 
    /**Method to retrieve data from the database. */
    @Override
    public void retrieve() throws SQLException{
        ResultSet resultData = queryStatement.executeQuery(queryString);
        
        resultData.beforeFirst();
        
        while(resultData.next()){
            customerList.add(new Customer(resultData));
        }
        
    }
    
    
    /**Method to retrieve new data from the database.
     * Used to get new data without having to make a whole new CustomerCRUD object.
     @param query The new string query. */
    @Override
    public void retrieve(String query) throws SQLException{
        this.queryString = query;
        retrieve();
        
    }
    
    /** * Static factory method. Creates new CustoemrCRUD instance and retrieves data.
     * @param conn The connection to the database.
     * @param query The string containing SELECT query to obtain initial data.
     * @return New customerCRUD with populated customerList
     * @throws java.sql.SQLException
     */
    static public CustomerCRUD makeCustomerCRUD(Connection conn, String query) throws SQLException{
        System.out.println("##################DEBUG MakeCustomerCRUD has started######################");
        CustomerCRUD out = new CustomerCRUD(conn, query);
        out.retrieve();
        return out;
    }
    
    
    
    /**Method to create a new customer. Note that Customer ID is auto-incremented.
     @param customerName The name of the customer
     @param address The address of the customer
     @param postalCode The postal code of the customer
     @param phone String to become phone number.
     @param division The first-level division to which the customer is associated.
     * @throws java.sql.SQLException*/
    public void create(String customerName, String address, String postalCode, String phone, FirstLevelDivision division) throws SQLException{
        createStatement.setString(1, customerName);
        createStatement.setString(2, address);
        createStatement.setString(3, postalCode);
        createStatement.setString(4, phone);
        createStatement.setInt(5, division.getDivisionId());
        createStatement.executeUpdate();
        createStatement.clearParameters();
        
        int nextId = customerList.get(customerList.size() - 1).getCustomerId() + 1;
        customerList.add(new Customer(nextId,customerName, address, postalCode, phone, division));
        
    }
    
    /** * Method to update a customer with a given ID. Current version assumes cursor of resultSet
     is already pointed at row to be changed. Current version automatically updates database.
     @param customerId The id of the customer to be updated.
     @param customerName The new name of the customer
     @param address The address of the customer
     @param postalCode The postal code of the customer
     @param phone String to become phone number.
     @param division The first-level division to which the customer is associated.
     * @throws java.sql.SQLException
     */
    public void update(int customerId, String customerName, String address, String postalCode, String phone, FirstLevelDivision division) throws SQLException{
        updateStatement.setString(1, customerName);
        updateStatement.setString(2, address);
        updateStatement.setString(3, postalCode);
        updateStatement.setString(4, phone);
        updateStatement.setInt(5, division.getDivisionId());
        updateStatement.setInt(6, customerId);
        
        updateStatement.executeUpdate();        
        updateStatement.clearParameters();
        
        for(Customer c : customerList){
            if(c.getCustomerId() == customerId){
                c.setCustomerName(customerName);
                c.setAddress(address);
                c.setPhone(phone);
                c.setPostalCode(postalCode);
                c.setDivisionId(division);
            }
        }
        
    }
    
    
    /**Method to delete a customer. 
     @param id The Customer_ID of the customer to be deleted.*/
    @Override
    public void delete(int id) throws SQLException{
        deleteStatement.setInt(1, id);
        deleteStatement.executeUpdate();
        deleteStatement.clearParameters();
        
        for(Customer c : customerList){
            if(c.getCustomerId() == id){
                customerList.remove(c);
            }
        }
    }
    
   
    
    
    @Override
    public void printList() {
        System.out.print("\nCUSTOMER\n");
        for(Customer c : customerList){
            c.print();
        }
    }
    
    //Commit and rollback are disabled. 
    
    /*@Override
    public void rollback() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    /*@Override
    public void commit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/


    
}
