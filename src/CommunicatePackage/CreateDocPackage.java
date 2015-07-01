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
public class CreateDocPackage implements Serializable{
    public int idOwner;
    public String title;
    
    public CreateDocPackage(int idOwner, String title){
        this.idOwner = idOwner;
        this.title = title;
    }    
}
