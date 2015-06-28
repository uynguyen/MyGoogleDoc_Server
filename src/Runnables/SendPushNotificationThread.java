/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import Bus.Global;
import CommunicatePackage.InvitePackage;

/**
 *
 * @author Thanh Tung
 */
public class SendPushNotificationThread implements Runnable{
    
    Thread t;
    InvitePackage invitePackage;
    String username;
    
    public SendPushNotificationThread(InvitePackage invitePackage, String username){
        this.invitePackage = invitePackage;
        this.username = username;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        Global.notificationPusher.Notify(invitePackage, username);
    }
    
}
