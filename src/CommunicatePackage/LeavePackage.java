/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommunicatePackage;

import java.io.Serializable;

/**
 *
 * @author Thanh Tung
 */
public class LeavePackage implements Serializable{
    public int clientID;
    public String docCode;
    public LeavePackage(int clientID, String docCode){
        this.clientID = clientID;
        this.docCode = docCode;
    }
}
