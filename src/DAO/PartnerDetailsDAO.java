/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.AccountDAO.checkExistAccount;
import java.io.File;
import java.sql.ResultSet;

/**
 *
 * @author UyNguyen.ITUS
 */
public class PartnerDetailsDAO {

    private static MySQLConnectionHelper connectionHelper = new MySQLConnectionHelper();

    public static Boolean removeMember(int id_acc, String doc_Code) {
        connectionHelper.openConnection();
        try {

            String strSQLTemp = "DELETE FROM partnerdetails t where t.id_member ='" + id_acc + "' and t.doc_code ='" + doc_Code + "'";
            boolean result = connectionHelper.excuteNonQuery(strSQLTemp);

            connectionHelper.closeConnection();

            return result;

        } catch (Exception e) {

            connectionHelper.closeConnection();
            e.printStackTrace();
            return false;
        }
    }

    public static boolean insertMember(String doc_Code, int id_Acc) {

        try {
            connectionHelper.openConnection();

            String strSQL = "INSERT INTO partnerdetails(doc_code,id_member) "
                    + " VALUES ( '" + doc_Code + "','" + id_Acc + "')";

            boolean result = connectionHelper.excuteNonQuery(strSQL);
            connectionHelper.closeConnection();
            return result;

        } catch (Exception e) {
            connectionHelper.closeConnection();
            return false;
        }
    }

}
