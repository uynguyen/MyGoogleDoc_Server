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
public class SharePackage implements Serializable{    

    public int idClient;
    public String docCode;
    public String username;

    public SharePackage(int idClient, String docCode, String username) {
        this.idClient = idClient;
        this.docCode = docCode;
        this.username = username;
    }
    
}
