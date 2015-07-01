/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.StyledEditorKit.StyledTextAction;

/**
 *
 * @author UyNguyen.ITUS
 */
public class ActionFormat extends Action {



    public ActionFormat(AttributeSet as) {
        super(as);
     
    }

    @Override

    public void onDraw(JTextPane textPane) {     
        System.err.println("Format: " + _startPosition + "->" + _endPosition);
        textPane.getStyledDocument().setCharacterAttributes(_startPosition, _endPosition - _startPosition, _attributeset, false);
    }


    /**
     * @return the _action
     */
    public AttributeSet getAction() {
        return _attributeset;
    }

    /**
     * @param _action the _action to set
     */
    public void setAction(AttributeSet _action) {
        this._attributeset = _action;
    }

 
  

}
