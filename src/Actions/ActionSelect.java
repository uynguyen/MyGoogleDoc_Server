/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

/**
 *
 * @author UyNguyen.ITUS
 */
public class ActionSelect extends Action{

    public ActionSelect(AttributeSet attributeset) {     
        super(attributeset);
         setColor(Color.orange);
    }  
    
    @Override
    public void onDraw(JTextPane textPane) {
       System.err.println("Select: " + _startPosition + "->" + _endPosition);
       textPane.getHighlighter().removeAllHighlights();
       int start = textPane.getSelectionStart();
      int end = textPane.getSelectionEnd();
       if (_startPosition == _endPosition){  
            SwingUtilities.invokeLater(()->{
               try {    
//                   if (textPane.getStyledDocument().getLength() == 0) return;
//                   if (textPane.getStyledDocument().getLength() == _endPosition)
//                   {
//                       _startPosition--;
//                   }
//                   else
//                       _endPosition++;
               textPane.getHighlighter().addHighlight(_startPosition, _endPosition,
                       new DefaultHighlighter.DefaultHighlightPainter(getColor()));                     
           } catch (BadLocationException ex) {
               Logger.getLogger(ActionSelect.class.getName()).log(Level.SEVERE, null, ex);
           }
               });    
             
       }
       else{
           
           SwingUtilities.invokeLater(()->{
              try {              
               textPane.getHighlighter().addHighlight(_startPosition, _endPosition,
                       new DefaultHighlighter.DefaultHighlightPainter(getColor()));
               
           } catch (BadLocationException ex) {
               Logger.getLogger(ActionSelect.class.getName()).log(Level.SEVERE, null, ex);
           }
               });   
           
          
        
       }    
       
          textPane.invalidate();
          textPane.setSelectionStart(start);
          textPane.setSelectionEnd(end);
    }
    
}
