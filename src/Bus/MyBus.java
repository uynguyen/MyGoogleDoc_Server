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
import Threads.SuperServerThread;
import java.util.ArrayList;

/**
 *
 * @author UyNguyen.ITUS
 */
public class MyBus {

    public static Account checkLoginInfo(String username, String password) {
        return AccountDAO.checkLoginInfo(username, password);
    }

    public static boolean register(Account account, String password) {
        return AccountDAO.register(account, password);
    }

    public static ArrayList<Document> getListDocument(int IDAcount) {
        return DocumentDAO.getListDocuments(IDAcount);

    }
    public static String createNewDocument(int id_Owner, String title )
    {
        return DocumentDAO.createNewDocument(id_Owner, title);
    }
    
    
  
}
