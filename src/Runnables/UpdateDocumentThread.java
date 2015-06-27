/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import Bus.MyBus;
import CustomComponents.StyledTextEditorOnServer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author UyNguyen.ITUS
 */
public class UpdateDocumentThread implements Runnable {

    private Thread t;
    private String _docCode;
    private StyledTextEditorOnServer _editor;
    private int _interval;

    public UpdateDocumentThread(String doc_Code, StyledTextEditorOnServer editor, int interval) {
        this._docCode = doc_Code;
        this._editor = editor;
        this._interval = interval;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {

        while (true) {
            try {

                Thread.sleep(_interval * 1000);
                MyBus.updateDocument(_docCode, _editor.getHTMLString());
            } catch (InterruptedException ex) {
                Logger.getLogger(UpdateDocumentThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
