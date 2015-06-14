/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.CommonConstants;

/**
 *
 * @author UyNguyen.ITUS
 */
public class MySQLConnectionHelper {

    private Connection con = null;

    public void openConnection() {
        try {
            Class.forName(CommonConstants._classNameDriver);
            con = DriverManager.getConnection(CommonConstants._urlConnection, CommonConstants._userConnection, CommonConstants._passwordConnection);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet excuteQuery(String sql) {
        try {
            Statement state = con.createStatement();
            return state.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Boolean excuteNonQuery(String sql) {
        try {

            Statement state = con.createStatement();
            state.execute(sql);
            
            
            
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
            //Logger.getLogger(MySQLConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    


}
