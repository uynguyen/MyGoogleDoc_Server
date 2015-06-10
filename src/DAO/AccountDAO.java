/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Pojo.Account;
import java.sql.ResultSet;

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
            String strSQL = "select * from accounts acc where acc.UserName = '" + username + "'";

            ResultSet resultSet = connectionHelper.excuteQuery(strSQL);
            while (resultSet.next()) {
                String pass = resultSet.getString("Password");
                if (password.compareTo(pass) == 0) {// Đúng
                    int ID = resultSet.getInt("ID");

                    String email = resultSet.getString("Email");
                    String avatar = resultSet.getString("Avatar");

                    result = new Account(username, avatar, email);
                }

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
            String strSQL = "select * from accounts acc where acc.UserName = '" + username + "'";

            ResultSet resultSet = connectionHelper.excuteQuery(strSQL);
            while (resultSet.next()) {
                connectionHelper.closeConnection();
                return true;

            }

        } catch (Exception e) {
            connectionHelper.closeConnection();
            return false;
        }
        return false;
    }

    public static boolean register(Account account, String password) {

        if (!checkExistAccount(account.getUsername())) {
            try {
                connectionHelper.openConnection();

                String strSQL = "INSERT INTO accounts(UserName, Password, Avatar, Email) "
                        + " VALUES ( '" + account.getUsername() + "'," + password + ",'" + account.getAvatar() + "','" + account.getEMail() + "')";

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

}
