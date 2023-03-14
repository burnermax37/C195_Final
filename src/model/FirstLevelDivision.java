/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *Class to represent first level divisions. Mainly used to prevent user from 
 * creating customers with invalid first-level divisions.
 * @author Maxwell Burner
 */
public class FirstLevelDivision {
    private final int divisionId;
    private String divisionName;
    private int countryId;
    
    /**Unused constructor using integers and a String.
     @param divisionId The integer to become the division ID.
     @param name The string to become the division name.
     @param countryId the integer to become the country id.*/
    public FirstLevelDivision(int divisionId, String name, int countryId){
        this.divisionId = divisionId;
        this.divisionName = name;
        this.countryId = countryId;
    }
    
    /** * Constructor using resultSet with first level division data.The internal pointer
     of the result set must already be pointing at the row to be converted into a
     FirstLevelDivision object.
     @param divisionData Resultset with data on first level divisions.
     * @throws java.sql.SQLException*/
    public FirstLevelDivision(ResultSet divisionData) throws SQLException{
        this.divisionId = divisionData.getInt("Division_ID");
        this.divisionName = divisionData.getString("Division");
        this.countryId = divisionData.getInt("Country_ID");
    }

    /**Getter for DivisionID.
     @return Division ID as integer.*/
    public int getDivisionId() {
        return divisionId;
    }

    /**Getter for Division Name.
     @return Name of division as String.*/
    public String getDivisionName() {
        return divisionName;
    }

    /**Getter for country Id.
     @return Division's country ID as integer.*/
    public int getCountryId() {
        return countryId;
    }

    /**Unused setter for division name. 
     @param divisionName The string to become the new name of the division.*/
    /*public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }*/
    
    
    /**Unused "setter" for country ID.
     @param country The country to which the division will be associated.
     Country ID of passed country is assigned to countryId parameter of this division,
     ensuring that no division has a country ID for a country that does not exist.*/
    /*public void associateCountry(Country country){
        this.countryId = country.getCountryId();
    }*/
    
    /**Debug method to print Division data to console. */
    public void print(){
        String base = "Division_ID: %d,\t"+
                "Division_Name: %s,\t"+
                "Country_ID: %d";
        System.out.println(String.format(base, divisionId, divisionName, countryId));
    }
    
}
