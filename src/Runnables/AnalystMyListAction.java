/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import Actions.Action;
import Actions.ActionInsert;
import java.util.ArrayList;

/**
 *
 * @author UyNguyen.ITUS
 */
public class AnalystMyListAction implements Runnable {

    ArrayList<Action> _lstMyListAction;
    Thread t;

    public AnalystMyListAction(ArrayList<Action> lstMyListAction) {
        _lstMyListAction = lstMyListAction;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
       
        for (Action action : _lstMyListAction) {

            String word = "";
            if (action instanceof ActionInsert) {
                ActionInsert temp = (ActionInsert) action;
               // if (temp.getContent().length() < 5) {
                     System.out.println(temp.getContent());
                    word += temp.getContent();
                //} else {

               // }
            } else {

              

                System.out.println(word);
                word = "";

            }
        }
    }
}
