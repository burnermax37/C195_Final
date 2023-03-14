/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;

/**
 *
 * @author Maxwell Burner
 */
public interface Connected {
    void receiveConnection(Connection conn);
}
