/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bus;

import Actions.Action;
import Actions.ActionInsert;
import DAO.AccountDAO;
import DAO.CollaborationDAO;
import DAO.DocumentDAO;
import DAO.InviteDAO;
import DAO.PartnerDetailsDAO;
import Pojo.Account;
import Pojo.Document;
import Pojo.Invite;
import Runnables.AnalystMyListAction;
import Runnables.ClientReceiveThread;
import Runnables.SuperServerThread;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Queue;

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

    synchronized public static String createNewDocument(int id_Owner, String title) {
        return DocumentDAO.createNewDocument(id_Owner, title);
    }

    synchronized public static String openDocument(String doc_Code) {
        return DocumentDAO.openDocument(doc_Code);

    }

    synchronized public static String resetPass(String username) {

        if (isExistUserName(username)) {
            String emailAddress = AccountDAO.findEmailAddress(username);
            bus.MySendingEmail sendEMail = new bus.MySendingEmail();

            String newPass = randomPass();

            boolean checked = renewPassword(username, newPass);
            if (checked) {
                boolean result = sendEMail.sendEmailForgotPass(emailAddress, newPass);
                return "Reset password success!\nPlease check your email!";

            } else {
                return "Reset password error!\nPlease try again!";
            }
        }

        return "Username doesn't exist";

    }

    private static Boolean isExistUserName(String userName) {

        ArrayList<String> lstCus = AccountDAO.getAllCusUserName();
        return lstCus.contains(userName);

    }

    private static String randomPass() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    public static Boolean renewPassword(String username, String password) {
        return AccountDAO.renewPass(username, password);
    }

    public static Boolean updatePassword(String username, String newPassword) {
        return AccountDAO.updatePassword(username, newPassword);
    }

    public static Boolean deleteDocument(String doc_Code) {

        return DocumentDAO.deleteDocument(doc_Code);

    }

    public static Boolean leaveDocument(int id_acc, String doc_Code) {

        return PartnerDetailsDAO.removeMember(id_acc, doc_Code);

    }

    public static Boolean shareDocument(String doc_code, int id_sender, String user_Receiver) {

        return CollaborationDAO.shareDocument(doc_code, id_sender, user_Receiver);
    }

    public static ArrayList<Invite> getMyInvite(int id_acc) {
        return InviteDAO.getMyInvite(id_acc);
    }

    public static boolean acceptInvite(int id, int id_Acc, String doc_Code) {

        if (InviteDAO.updateInvite(id, true)) {
            return PartnerDetailsDAO.insertMember(doc_Code, id_Acc);

        }
        return false;

    }
    
    public static String getUsernameByID(int id){
        return AccountDAO.getUsernameByID(id);
    }

    public static Account getAccountByUsername(String username){
        return AccountDAO.getAccountByUsername(username);
               
    }
    public static boolean insertMemberIntoDocument(String doc_Code, int id_member){
        return PartnerDetailsDAO.insertMember(doc_Code, id_member);
    }
    
    public static boolean rejectInvite(int id) {
        return InviteDAO.deleteInvite(id);
    }

    public static Document getDocumentByCode(String doc_Code) {
        return DocumentDAO.getDocumentByCode(doc_Code);
    }

    public static boolean updateDocument(String _docCode, String _content) {
        String path = MyBus.getDocumentByCode(_docCode).getPath();
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path)));

            out.print(_content);

            out.close();
            return true;
        } catch (IOException e) {
            Logger.getLogger(MyBus.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

    }

    public static boolean analystArrayListAction(Queue<Action> lstAction) {

        //Split action of each member
        HashMap<String, ArrayList<Action>> items = new HashMap<String, ArrayList<Action>>();
        while(!lstAction.isEmpty()) {
            try {
                Action action = lstAction.dequeue();
                String userNameAcc = action.getUserName();
                if (items.containsKey(userNameAcc)) {
                    
                    items.get(userNameAcc).add(action);
                } else {
                    ArrayList<Action> temp = new ArrayList<>();
                    temp.add(action);
                    items.put(userNameAcc, temp);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(MyBus.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        for (String key : items.keySet()) {

            ArrayList<Action> myListAction = items.get(key);
            AnalystMyListAction analystMyListAction = new AnalystMyListAction(myListAction);

        }
        return true;

    }

}
