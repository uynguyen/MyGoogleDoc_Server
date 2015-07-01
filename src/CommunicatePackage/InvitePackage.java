/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommunicatePackage;

import Pojo.Document;
import java.io.Serializable;

/**
 *
 * @author Thanh Tung
 */
public class InvitePackage implements Serializable{
    public boolean quit;
    public String senderName;
    public Document document;
    
    public InvitePackage(boolean quit, String sender, Document doc){
        this.quit = quit;
        this.senderName = sender;
        this.document = doc;
    }
}
