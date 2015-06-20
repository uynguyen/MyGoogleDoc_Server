/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

/**
 *
 * @author UyNguyen.ITUS
 */
public class ActionDelete extends Action{

    public ActionDelete(AttributeSet attributeset) {
        super(attributeset);
    }

    
    @Override
    public void onDraw(JTextPane textPane) {
        try {
            textPane.getDocument().remove(_startPosition, _endPosition  - _startPosition);
             int currentPos = textPane.getCaretPosition();
            if(_startPosition < currentPos){
                int newPos = currentPos - (_endPosition  - _startPosition);
                newPos = (newPos < 0)? 0 : newPos;
                textPane.setCaretPosition(newPos);
            }
            textPane.validate();
        } catch (BadLocationException ex) {
            Logger.getLogger(ActionDelete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
