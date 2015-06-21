/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Pojo.Account;
import Pojo.Document;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
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

                String code = resultSet.getString("doc_code");
                Document doc = new Document(ID, name, path, dateCreate, IDAccount, code);
                result.add(doc);
            }

            //--------------------
            //-----------Lấy những tài liệu thằng này có tham gia cộng tác
            strSQL = "select * from documents t where t.id_owner <> '" + IDAccount + "'";

            resultSet = connectionHelper.excuteQuery(strSQL);
            while (resultSet.next()) {
                String code = resultSet.getString("doc_code");

                if (checkBelongGroup(code, IDAccount)) {
                    int ID = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String path = resultSet.getString("path");
                    Date dateCreate = resultSet.getDate("date_create");
                    int idOwner = resultSet.getInt("id_owner");
                    Document doc = new Document(ID, name, path, dateCreate, idOwner, code);
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
    public static boolean checkBelongGroup(String doc_Code, int IDAccount) {

        connectionHelper.openConnection();
        try {

            String strSQLTemp = "select * from partnerdetails t where t.doc_code ='" + doc_Code + "' and t.id_member ='" + IDAccount + "'";
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
            String path = "Document\\" + title.replace(' ', '_') + doc_code + ".html";
            File file = new File(path);

            boolean result = file.createNewFile();
            if (result) {

                String sql = "INSERT INTO documents(name, path, date_create, id_owner, doc_code)"
                        + " VALUES ( '" + title + "','" + path + "','" + cal.getTime() + "','" + id_Owner + "','" + doc_code + "')";
                if (connectionHelper.excuteNonQuery(sql)) {
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

    public static String openDocument(String doc_Code) {

        connectionHelper.openConnection();
        try {
            String path = "";
            String strSQLTemp = "select * from documents t where t.doc_code ='" + doc_Code + "'";
            ResultSet resultSetTemp = connectionHelper.excuteQuery(strSQLTemp);
            while (resultSetTemp.next()) {
                connectionHelper.closeConnection();
                path = resultSetTemp.getString("path");
                break;
            }
            String result = "";
            if (path != "") {

                FileReader fr = new FileReader(path);
                BufferedReader br = new BufferedReader(fr);

                String aline;
                while ((aline = br.readLine()) != null) {
                    result += aline;
                }

                br.close();
            }

            return result;

        } catch (Exception e) {

            connectionHelper.closeConnection();
            e.printStackTrace();
            return "";
        }

    }

    public static boolean deleteDocument(String doc_Code) {

        connectionHelper.openConnection();
        try {
            String path = "";

            String strSQLTemp = "select * from documents t where t.doc_code ='" + doc_Code + "'";
            ResultSet resultSetTemp = connectionHelper.excuteQuery(strSQLTemp);
            while (resultSetTemp.next()) {

                path = resultSetTemp.getString("path");

                break;
            }
            String result = "";
            if (path != "") {

                strSQLTemp = "DELETE  FROM partnerdetails t where t.doc_code ='" + doc_Code + "'";

                boolean t2 = connectionHelper.excuteNonQuery(strSQLTemp);

                strSQLTemp = "DELETE  FROM collaboration t where t.doc_code ='" + doc_Code + "'";

                boolean t3 = connectionHelper.excuteNonQuery(strSQLTemp);

                strSQLTemp = "DELETE  FROM documents t where t.doc_code ='" + doc_Code + "'";

                boolean t1 = connectionHelper.excuteNonQuery(strSQLTemp);

                connectionHelper.closeConnection();
                if (t1 && t2 && t3) {

                    File file = new File(path);

                    if (file.delete()) {
                        return true;
                    } else {
                        return false;
                    }

                }

            }

            connectionHelper.closeConnection();

            return false;

        } catch (Exception e) {

            connectionHelper.closeConnection();
            e.printStackTrace();
            return false;
        }

    }

//    public static int getGroupDocument(String doc_Code) {
//         connectionHelper.openConnection();
//        try {
//
//            String strSQLTemp = "select * from documents t where t.doc_code ='" + doc_Code + "'";
//            ResultSet resultSetTemp = connectionHelper.excuteQuery(strSQLTemp);
//            while (resultSetTemp.next()) {
//                int id = resultSetTemp.getInt("id_partners");
//                connectionHelper.closeConnection();
//                return id;
//            }
//            return -1;
//        } catch (Exception e) {
//
//            connectionHelper.closeConnection();
//            e.printStackTrace();
//            return -1;
//        }
//     
//    }
//    //Update group về -1, tức là không có share cho ai hết
//    public static boolean updateGroupByDefault(String doc_Code) {
//      try {
//            connectionHelper.openConnection();
//
//            String strSQL = "update documents set id_partners='-1' where doc_Code = '" + doc_Code + "'";
//            connectionHelper.excuteNonQuery(strSQL);
//
//            connectionHelper.closeConnection();
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
    static Document getDocumentByCode(String doc_Code) {
        try {
            connectionHelper.openConnection();
            String strSQLTemp = "select * from documents t where t.doc_code ='" + doc_Code + "'";
            ResultSet resultSet = connectionHelper.excuteQuery(strSQLTemp);
            while (resultSet.next()) {
                int ID = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String path = resultSet.getString("path");
                Date dateCreate = resultSet.getDate("date_create");
                int id_Owner = resultSet.getInt("id_owner");
                String code = resultSet.getString("doc_code");
                connectionHelper.closeConnection();
                return new Document(ID, name, path, dateCreate, id_Owner, code);

            }
            connectionHelper.closeConnection();
            return null;
        } catch (Exception e) {
            connectionHelper.closeConnection();
            return null;
        }

    }
}
