/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.File;
import java.sql.ResultSet;

/**
 *
 * @author UyNguyen.ITUS
 */
public class PartnerDetailsDAO {

    private static MySQLConnectionHelper connectionHelper = new MySQLConnectionHelper();

    public static Boolean removeMember(int id_acc, int id_group, String doc_Code) {
        connectionHelper.openConnection();
        try {

            String strSQLTemp = "SELECT COUNT(*) AS total FROM partnerdetails t where t.id_group ='" + id_group + "'";

            ResultSet rSet = connectionHelper.excuteQuery(strSQLTemp);
            rSet.next();
            int total = rSet.getInt("total");
            
            
            if(total == 1){// Nếu như nhóm chỉ còn 1 người và người đó rời khỏi thì cập nhật id group của doc về -1
                
                DocumentDAO.updateGroupByDefault(doc_Code);
                
                
                
                
            }
            strSQLTemp = "DELETE FROM partnerdetails t where t.id_member ='" + id_acc + "'";
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
