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
public class CreateDocReturnPackage implements Serializable
{
    public String docCode;
    public int port;
    
    public CreateDocReturnPackage(String docCode, int port){
        this.docCode = docCode;
        this.port = port;
    }
}
