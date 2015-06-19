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
 * @author UyNguyen.ITUS
 */
public class ActionSelect extends Action{

    public ActionSelect(AttributeSet attributeset) {
        super(attributeset);
    }
    
   
    
    @Override
    public void onDraw(JTextPane textPane) {
        textPane.getStyledDocument().setCharacterAttributes(_startPosition, _endPosition - _startPosition, _attributeset, false);
    }
    
}
