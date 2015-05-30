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
import java.util.ArrayList;

/**
 *
 * @author UyNguyen.ITUS
 */
public class MyBus {

    public Account checkLoginInfo(String username, String password) {
        return AccountDAO.checkLoginInfo(username, password);
    }

    public boolean register(Account account, String password) {
        return AccountDAO.register(account, password);
    }

    public ArrayList<Document> getListDocument(int IDAcount) {
        return DocumentDAO.getListDocuments(IDAcount);

    }
}
