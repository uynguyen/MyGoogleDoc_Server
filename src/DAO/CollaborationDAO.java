/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author UyNguyen.ITUS
 */
public class CollaborationDAO {

    private static MySQLConnectionHelper connectionHelper = new MySQLConnectionHelper();

    public static Boolean shareDocument(String doc_code, int id_sender, int id_receiver) {

        connectionHelper.openConnection();
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();

            String sql = "INSERT INTO collaboration(doc_code, id_acc_sender, id_acc_receiver, date_time_invite, state)"
                    + " VALUES ( '" + doc_code + "','" + id_sender + "','" + id_receiver + "','" + cal.getTime() + "','" + false + "')";

            boolean result = connectionHelper.excuteNonQuery(sql);
            connectionHelper.closeConnection();
            return result;
        } catch (Exception e) {

            connectionHelper.closeConnection();
            e.printStackTrace();
            return false;
        }

    }

}
