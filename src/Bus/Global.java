/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bus;

import CustomComponents.StyledTextEditorOnServer;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;


/**
 *
 * @author Thanh Tung
 */
public class Global {
    public static ConcurrentHashMap documentPort = new ConcurrentHashMap();
    public static NotificationPusher notificationPusher = new NotificationPusher();;
    public static int MainServerPort = 51399;
    public static int SuperServerPort = 13599;
}
