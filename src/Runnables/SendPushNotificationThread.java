/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import Bus.Global;
import CommunicatePackage.InvitePackage;
import Pojo.Document;

/**
 *
 * @author Thanh Tung
 */
public class SendPushNotificationThread implements Runnable{
    
    Thread t;
    Document doc;
    String username;    

    SendPushNotificationThread(String sender, Document doc) {
        this.doc = doc;
        this.username = sender;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        Global.notificationPusher.Notify(new InvitePackage(false, username, doc), username);
    }
    
}
