/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bus;

import DAO.AccountDAO;
import DAO.DocumentDAO;
import Pojo.Account;
import Pojo.Document;
import Runnables.SuperServerThread;
import java.util.ArrayList;

/**
 *
 * @author UyNguyen.ITUS
 */
public class MyBus {

    synchronized public static Account checkLoginInfo(String username, String password) {
        return AccountDAO.checkLoginInfo(username, password);
    }

    synchronized public static boolean register(Account account, String password) {
        return AccountDAO.register(account, password);
    }

    synchronized public static ArrayList<Document> getListDocument(int IDAcount) {
        return DocumentDAO.getListDocuments(IDAcount);

    }
    synchronized public static String createNewDocument(int id_Owner, String title )
    {
        return DocumentDAO.createNewDocument(id_Owner, title);
    }
    
    
    synchronized public static String openDocument(String doc_Code){
      return DocumentDAO.openDocument(doc_Code);
      
  }
}
