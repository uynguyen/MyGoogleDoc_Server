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

    public boolean excuteStoreProc(String storeProcName, ArrayList<Object> params) {
        switch (storeProcName) {
            case "insertnewcustomer": {
                CallableStatement cs;
                try {
                    cs = con.prepareCall("{call insertnewcustomer(?,?,?,?,?,?,?,?,?,?)}");

                    cs.setString(1, (String) params.get(0));
                    cs.setBoolean(2, (Boolean) params.get(1));
                    cs.setString(3, (String) params.get(2));
                    cs.setString(4, (String) params.get(3));
                    cs.setString(5, (String) params.get(4));
                    cs.setString(6, (String) params.get(5));
                    cs.setString(7, (String) params.get(6));
                    cs.setString(8, (String) params.get(7));
                    cs.setInt(9, (int) params.get(8));;
                    cs.setString(10, (String) params.get(9));

                    cs.execute();
                    

                } catch (SQLException e) {
                    System.out.println(e.toString());
                    return false;
                }

            }

        }
        return true;
    }
}
