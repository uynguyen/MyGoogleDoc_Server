/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Pojo.Account;
import Pojo.Document;
import java.io.File;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author UyNguyen.ITUS
 */
public class DocumentDAO {

    private static MySQLConnectionHelper connectionHelper = new MySQLConnectionHelper();

    public static ArrayList<Document> getListDocuments(int IDAccount) {

        ArrayList<Document> result = new ArrayList<>();

        connectionHelper.openConnection();

        try {
            //---------Lấy những tài liệu thằng này làm chủ
            String strSQL = "select * from documents doc where doc.id_owner = '" + IDAccount + "'";

            ResultSet resultSet = connectionHelper.excuteQuery(strSQL);
            while (resultSet.next()) {

                int ID = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String path = resultSet.getString("path");
                Date dateCreate = resultSet.getDate("date_create");

                int IDGroup = resultSet.getInt("id_partners");
                String code = resultSet.getString("doc_code");
                Document doc = new Document(ID, name, path, dateCreate, IDAccount, IDGroup, code);
                result.add(doc);
            }

            //--------------------
            //-----------Lấy những tài liệu thằng này có tham gia cộng tác
            strSQL = "select * from documents";

            resultSet = connectionHelper.excuteQuery(strSQL);
            while (resultSet.next()) {

                int IDGroup = resultSet.getInt("id_partners");

                if (checkBelongGroup(IDGroup, IDAccount)) {
                    int ID = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String path = resultSet.getString("path");
                    Date dateCreate = resultSet.getDate("date_create");
                    int idOwner = resultSet.getInt("id_owner");
                    String code = resultSet.getString("doc_code");
                    Document doc = new Document(ID, name, path, dateCreate, idOwner, IDGroup,code);
                    result.add(doc);
                }

            }

            //------------
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            connectionHelper.closeConnection();
            return result;
        }

    }

    //Hàm kiêm tra xem một tài khoản có thuộc về 1 nhóm người đang cộng tác một tài liệu không
    public static boolean checkBelongGroup(int IDGroup, int IDAccount) {

        connectionHelper.openConnection();
        try {

            String strSQLTemp = "select * from partnerdetails t where t.id_group ='" + IDGroup + "' and t.id_member ='" + IDAccount + "'";
            ResultSet resultSetTemp = connectionHelper.excuteQuery(strSQLTemp);
            while (resultSetTemp.next()) {
                connectionHelper.closeConnection();
                return true;
            }

        } catch (Exception e) {

            connectionHelper.closeConnection();
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static String createNewDocument(int id_Owner, String title) {

        connectionHelper.openConnection();
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            String doc_code = String.valueOf(cal.getTime().getTime());
            String path = "Document\\" + title + doc_code + ".html";
            File file = new File(path);

            boolean result = file.createNewFile();
            if (result) {

                String sql = "INSERT INTO documents(name, path, date_create, id_owner, id_partners, doc_code)"
                        + " VALUES ( '" + title + "','" + path + "','" + cal.getTime() + "','" + id_Owner + "','" + "-1" + "','" + doc_code +"')";
                if(connectionHelper.excuteNonQuery(sql))
                {
                    return doc_code;
                }

            } 
            return "";
 

        } catch (Exception e) {

            connectionHelper.closeConnection();
            e.printStackTrace();
            return "";
        }

    }
}
