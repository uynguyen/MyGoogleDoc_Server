/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bus;

import CommunicatePackage.InvitePackage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Tung
 */
public class NotificationPusher {
    
    ConcurrentHashMap<String, ObjectOutputStream> clients_out;
    ConcurrentHashMap<String, ObjectInputStream> clients_in;
    
    public void Notify(int id, Object message, String username){
        try {
            ObjectOutputStream oos = clients_out.get(username);
            ObjectInputStream ois = clients_in.get(username);
            InvitePackage invitePackage = (InvitePackage)message;
            oos.writeObject(invitePackage);
            oos.flush();
            
            boolean rs = ois.readBoolean();
            
            if(rs){
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(NotificationPusher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
