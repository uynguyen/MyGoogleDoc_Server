/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

/**
 *
 * @author UyNguyen.ITUS
 */
public enum EnumUserAction {
    
    CREATEDOC(0), OPENDOC(1), REGISTER(2), LOGIN(3), RESETPASS(4), REPLYINVITE(5), SHARE(6);
    //MEETING(1), EVENT(2), ANNIVERSARY(3),
    private int value;

    private EnumUserAction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    
}
