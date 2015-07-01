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
    String sender;    
    String receiver;

    SendPushNotificationThread(String sender, Document doc, String receiver) {
        this.doc = doc;
        this.sender = sender;
        this.receiver = receiver;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        Global.notificationPusher.Notify(new InvitePackage(false, sender, doc), receiver);
    }
    
}
