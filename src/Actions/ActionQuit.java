/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;

/**
 *
 * @author Thanh Tung
 */
public class ActionQuit extends Action{
    String leftUser;

    public ActionQuit(AttributeSet attributeset, String leftuser) {
        super(attributeset);
        this.leftUser = leftuser;
    }

    @Override
    public void onDraw(JTextPane textPane) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public String getLeftUser() {
        return leftUser;
    }
    
}
