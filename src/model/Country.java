/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class to represent different countries. Mainly serves as a reference to prevent 
 * creation of First Level Divisions and Customers with invalid country IDs.
 * @author Maxwell Burner
 */
public class Country {
    private final int countryId;
    private String countryName;
    
    
    /**Constructor using integer and string. Currently disabled to prevent
     * creation of countries not already present in database.
     * @param id The integer to become the CountryID
     * @param Name The string to become the name of the country.
    */
    /*Country(int id, String name){
        this.countryId = id;
        this.countryName = name;
    }*/
    
    /** Constructor using a result set.The internal pointer of the provided resultSet
 must be pointing to the row from which a country object is to be made.
     @param countryData A resultSet from the country table
     * @throws java.sql.SQLException*/
    public Country(ResultSet countryData) throws SQLException{
        this.countryId = countryData.getInt("Country_ID");
        this.countryName = countryData.getString("Country");
    }

    /**Getter for CountryID.
     @return Country ID as integer.*/
    public int getCountryId() {
        return countryId;
    }

    /**Getter for Country Name. 
     @return country name as String.*/
    public String getCountryName() {
        return countryName;
    }
    
    /**Debug function to print country info to console. */
    public void print(){
        String base = "Country_ID: %d,\t"+
                "Country: %s";
        
        System.out.println(String.format(base, countryId, countryName));
    }
    
    
    
}
