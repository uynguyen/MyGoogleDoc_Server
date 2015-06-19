/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Pojo.Account;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author UyNguyen.ITUS
 */
public class AccountDAO {

    private static MySQLConnectionHelper connectionHelper = new MySQLConnectionHelper();

    public static Account checkLoginInfo(String username, String password) {

        Account result = null;
        try {
            connectionHelper.openConnection();
            String strSQL = "select * from accounts acc where acc.username = '" + username + "' and acc.password = md5('" + password + "')";

            ResultSet resultSet = connectionHelper.excuteQuery(strSQL);
            while (resultSet.next()) {

                int ID = resultSet.getInt("id");

                String email = resultSet.getString("email");
                String avatar = resultSet.getString("avatar");

                result = new Account(ID, username, avatar, email);

            }

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            connectionHelper.closeConnection();
            return result;
        }

    }

    public static boolean checkExistAccount(String username) {

        try {
            connectionHelper.openConnection();
            String strSQL = "select * from accounts acc where acc.username = '" + username + "'";

            ResultSet resultSet = connectionHelper.excuteQuery(strSQL);
            while (resultSet.next()) {
                connectionHelper.closeConnection();
                return true;

            }

        } catch (Exception e) {
            connectionHelper.closeConnection();
            return false;
        }
        connectionHelper.closeConnection();
        return false;
    }

    public static boolean register(Account account, String password) {

        if (!checkExistAccount(account.getUsername())) {
            try {
                connectionHelper.openConnection();

                String strSQL = "INSERT INTO accounts(username, password, avatar, email) "
                        + " VALUES ( '" + account.getUsername() + "',md5('" + password + "'),'" + account.getAvatar() + "','" + account.getEMail() + "')";

                connectionHelper.excuteNonQuery(strSQL);
                return true;

            } catch (Exception e) {
                connectionHelper.closeConnection();
                return false;
            }
        } else {
            return false;
        }

    }

    public static Boolean renewPass(String username, String password) {
        try {
            connectionHelper.openConnection();

            String strSQL = "update accounts set password=md5('" + password + "') where username = '" + username + "'";
            connectionHelper.excuteNonQuery(strSQL);

            connectionHelper.closeConnection();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static ArrayList<String> getAllCusUserName() {
        connectionHelper.openConnection();
        ArrayList<String> result = new ArrayList<>();
        try {

            String strSQL = "select acc.username from accounts acc";

            ResultSet resultSet = connectionHelper.excuteQuery(strSQL);
            while (resultSet.next()) {
                String userName = resultSet.getString("username");

                result.add(userName);
            }

        } catch (SQLException ex) {

        }
        connectionHelper.closeConnection();
        return result;
    }

    public static String findEmailAddress(String username) {
        try {
            connectionHelper.openConnection();

            String strSQL = "SELECT * FROM accounts WHERE username='" + username + "'";

            ResultSet resultSet = connectionHelper.excuteQuery(strSQL);
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                connectionHelper.closeConnection();
                return email;
            }

        } catch (Exception e) {
            connectionHelper.closeConnection();
            return "";
        }
        connectionHelper.closeConnection();
        return "success";
    }

    public static boolean updatePassword(String username, String newPassword) {

        return renewPass(username, newPassword);

    }

}
