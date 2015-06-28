/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bus;

import CommunicatePackage.InvitePackage;
import Pojo.Account;
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
    
    public NotificationPusher(){
        clients_in = new ConcurrentHashMap<>();
        clients_out = new ConcurrentHashMap<>();
    }
    
    public boolean CheckOnline(String username){
        return clients_in.containsKey(username);
    }
    
    synchronized public void Register(ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream, String username){
        clients_in.put(username, objectInputStream);
        clients_out.put(username, objectOutputStream);
        System.out.println("notification pusher size" + clients_in.size() + "/" + clients_out.size());
    }
    
    
    synchronized public void Unregister(String username) {
        try {
            ObjectOutputStream objectOutputStream = clients_out.get(username);
            ObjectInputStream objectInputStream = clients_in.get(username);
            objectInputStream.close();
            objectOutputStream.flush();
            objectOutputStream.close();
            clients_in.remove(username);
            clients_out.remove(username);
        } catch (IOException ex) {
            Logger.getLogger(NotificationPusher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    synchronized public void Notify(Object message, String username){
        try {
            ObjectOutputStream oos = clients_out.get(username);
            ObjectInputStream ois = clients_in.get(username);
            InvitePackage invitePackage = (InvitePackage)message;
            oos.writeObject(invitePackage);
            oos.flush();
            
            boolean rs = ois.readBoolean();
            boolean rs2 = false;
            if(rs){
                Account temp = MyBus.getAccountByUsername(username);
                rs2 = MyBus.insertMemberIntoDocument(username, temp.getID());
                
                oos.writeBoolean(rs2);
                oos.flush();                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(NotificationPusher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
