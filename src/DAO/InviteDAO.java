/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Pojo.Account;
import Pojo.Document;
import Pojo.Invite;
import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author UyNguyen.ITUS
 */
public class InviteDAO {

    private static MySQLConnectionHelper connectionHelper = new MySQLConnectionHelper();

    public static ArrayList<Invite> getMyInvite(int id_acc) {

        ArrayList<Invite> result = new ArrayList<>();
        try {
            connectionHelper.openConnection();
            String strSQL = "select * from collaboration colla where colla.id_acc_receiver = '" + id_acc + "' and colla.state = '" + false + "'";

            ResultSet resultSet = connectionHelper.excuteQuery(strSQL);
            while (resultSet.next()) {

                int ID = resultSet.getInt("id");
                String doc_Code = resultSet.getString("doc_code");
                Document doc = DocumentDAO.getDocumentByCode(doc_Code);

                int id_sender = resultSet.getInt("id_acc_sender");
                Date date_Invite = resultSet.getDate("date_time_invite");
                String username_Sender = AccountDAO.getUsernameByID(id_sender);

                Invite temp = new Invite(ID, doc_Code, id_sender, username_Sender, date_Invite, doc.getName());

                result.add(temp);
            }

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            connectionHelper.closeConnection();
            return result;
        }

    }

    public static boolean updateInvite(int id, boolean state) {

        try {
            connectionHelper.openConnection();
            String strSQL = "update collaboration set state='" + state + "' where id = '" + id + "'";

            boolean result = connectionHelper.excuteNonQuery(strSQL);
            connectionHelper.closeConnection();
            return result;

        } catch (Exception e) {
            connectionHelper.closeConnection();
            e.printStackTrace();
            return false;
        }

    }

    public static boolean deleteInvite(int id) {
        connectionHelper.openConnection();
        try {

            String strSQLTemp = "DELETE  FROM collaboration t where t.id ='" + id + "'";

            boolean result = connectionHelper.excuteNonQuery(strSQLTemp);
            
            connectionHelper.closeConnection();

            return result;

        } catch (Exception e) {

            connectionHelper.closeConnection();
            e.printStackTrace();
            return false;
        }
    }

}
