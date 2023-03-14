/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class to represent customers. 
 * @author Maxwell Burner
 */
public class Customer {
    private final int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private int divisionId;

    /**Constructor for a new customer, not yet in the database.
     @param customerId The customer ID
     @param customerName The name of the customer
     @param address The address of the customer
     @param postalCode The postal code of the customer
     @param phone String to become phone number.
     @param division The first-level division to which the customer is associated.*/
    public Customer(int customerId, String customerName, String address, String postalCode, String phone, FirstLevelDivision division) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionId = division.getDivisionId();
    }
    
    /** * Constructor for a customer already in the database.Internal pointer of 
 passed resultSet must be pointing to row corresponding to the customer to be
 created as a Customer instance.
     @param customerData ResultSet with data on customers
     * @throws java.sql.SQLException*/
    public Customer(ResultSet customerData) throws SQLException{
        this.customerId = customerData.getInt("Customer_ID");
        this.customerName = customerData.getString("Customer_Name");
        this.address = customerData.getString("Address");
        this.postalCode = customerData.getString("Postal_Code");
        this.phone = customerData.getString("Phone");
        this.divisionId = customerData.getInt("Division_ID");
    }

    /**Getter for customer Id.
     @return Customer ID as integer.*/
    public int getCustomerId() {
        return customerId;
    }

    /**Getter for customer name. 
     @return Customer name as String.*/
    public String getCustomerName() {
        return customerName;
    }

    /**Getter for customer address.
     @return Customer address as String.*/
    public String getAddress() {
        return address;
    }

    /**Getter for customer postal code.
     @return postalCode of customer as String.*/
    public String getPostalCode() {
        return postalCode;
    }

    /**Getter for phone number. 
     @return Phone number as String.*/
    public String getPhone() {
        return phone;
    }

    /**Getter for division ID.
     @return DivisionID of first level division associated to customer, as int.*/
    public int getDivisionId() {
        return divisionId;
    }

    /**Setter for customer name. 
     @param customerName String to become customer name.*/
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**Setter for customer address. 
     @param address String to become address. */
    public void setAddress(String address) {
        this.address = address;
    }

    /**Setter for postal code. 
     @param postalCode String to become postal code.*/
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**Setter for phone number. 
     @param phone String to become phone number. */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**"Setter" for division ID. Takes an existing division and assigns its ID
     * to division ID parameter; this ensures no customer can have a division ID for
     * a division that does not exist.
     @param division The division to which the customer is to be associated. */
    public void setDivisionId(FirstLevelDivision division) {
        this.divisionId = division.getDivisionId();
    }
    
    
    /**Debug method to print values to console. */
    public void print(){
        String base = "CustomerID: %d,\t"+
                "Customer_Name: %s,\t"+
                "Address: %s,\t"+
                "Postal_Code: %s,\t"+
                "Phone: %s,\t"+
                "Division_ID: %d";
        
        System.out.println(String.format(base, customerId, customerName, address, postalCode, phone, divisionId));
        
    }
    
    
    
    
        
}
