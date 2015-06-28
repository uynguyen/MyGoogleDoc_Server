/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bus;

import DAO.AccountDAO;
import DAO.CollaborationDAO;
import DAO.DocumentDAO;
import DAO.InviteDAO;
import DAO.PartnerDetailsDAO;
import Pojo.Account;
import Pojo.Document;
import Pojo.Invite;
import Runnables.SuperServerThread;
import java.util.ArrayList;
import java.util.Random;

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

    public static boolean rejectInvite(int id) {
        return InviteDAO.deleteInvite(id);
    }
    
    
    public static Document getDocumentByCode(String doc_Code){
        return DocumentDAO.getDocumentByCode(doc_Code);
    }
}
