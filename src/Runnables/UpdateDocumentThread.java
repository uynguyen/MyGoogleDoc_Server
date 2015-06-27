/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import Actions.Action;
import Bus.MyBus;
import CustomComponents.StyledTextEditorOnServer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Queue;

/**
 *
 * @author UyNguyen.ITUS
 */
public class UpdateDocumentThread implements Runnable {

    private Thread t;
    private String _docCode;
    private StyledTextEditorOnServer _editor;
    private int _interval;
    private Queue<Action> _lstAction;

    public UpdateDocumentThread(String doc_Code, StyledTextEditorOnServer editor, int interval) {
        this._docCode = doc_Code;
        this._editor = editor;
        this._interval = interval;
        _lstAction = new Queue<Action>();
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {

        while (true) {
            try {

                Thread.sleep(_interval * 1000);
                MyBus.updateDocument(_docCode, _editor.getHTMLString());
                Queue<Action> temp = new Queue<>();
                while (!_lstAction.isEmpty()) {
                    System.out.println("222222222222");
                    temp.enqueue(_lstAction.dequeue());
                }
                //synchronized(_lstAction){
                MyBus.analystArrayListAction(temp);
                //}

            } catch (InterruptedException ex) {
                Logger.getLogger(UpdateDocumentThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * @return the _lstAction
     */
    public Queue<Action> getLstAction() {
        return _lstAction;
    }

    /**
     * @param _lstAction the _lstAction to set
     */
    public void setLstAction(Queue<Action> _lstAction) {
        this._lstAction = _lstAction;
    }
}
