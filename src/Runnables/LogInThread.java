/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import Bus.MyBus;
import CommunicatePackage.LoginPackage;
import CommunicatePackage.LoginReturnPackage;
import Pojo.Account;
import Pojo.Document;
import Pojo.Invite;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThanhTung
 */
public class LogInThread implements Runnable {

    Thread t = null;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    public LogInThread(ObjectOutputStream output, ObjectInputStream input) {
        this.objectInputStream = input;
        this.objectOutputStream = output;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            //Receive client username and password for checking
            LoginPackage loginInfo = (LoginPackage) objectInputStream.readObject();

            //Check login information, null == fail
            Account result = MyBus.checkLoginInfo(loginInfo.username, loginInfo.password);

            if (result == null) {
                //Send fail signal back to client
                objectOutputStream.writeObject(new LoginReturnPackage(false, null, null, null));
                objectOutputStream.flush();
            } else {
                //Get Document list
                ArrayList<Document> userDocumentList = MyBus.getListDocument(result.getID());
                Document[] documentList = new Document[userDocumentList.size()];
                documentList = userDocumentList.toArray(documentList);
                ArrayList<Invite> userInviteList = MyBus.getMyInvite(result.getID());
                Invite[] inviteList = new Invite[userInviteList.size()];
                inviteList = userInviteList.toArray(inviteList);
                //Create return package
                LoginReturnPackage message = new LoginReturnPackage(true, result, documentList,inviteList);
                //Send success signal with user information and document list
                objectOutputStream.writeObject(message);
                objectOutputStream.flush();
            }

            //Close streams
            objectInputStream.close();
            objectOutputStream.flush();
            objectOutputStream.close();

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(LogInThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
