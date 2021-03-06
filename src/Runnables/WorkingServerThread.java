/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import Bus.Notifier;
import CustomComponents.StyledTextEditorOnServer;
import Pojo.Account;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Tung
 */
public class WorkingServerThread implements Runnable {

    Thread t;
    ServerSocket server;
    Notifier notifier;
    String docCode;
    StyledTextEditorOnServer textEditor;

    public WorkingServerThread(ServerSocket server, String docCode) {
        this.server = server;
        this.docCode = docCode;
        textEditor = new StyledTextEditorOnServer();
        notifier = new Notifier(docCode, textEditor);
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        do {
            try {
                //Open thread update Document
                UpdateDocumentThread documentThread = new UpdateDocumentThread(docCode, textEditor, 10);

                //accept a port
                Socket client = server.accept();

                System.out.println(client.getPort());

                //get outputstream
                ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
                oos.flush();

                //get inputStream
                ObjectInputStream ois = new ObjectInputStream(client.getInputStream());

                //Register client outputStream
                //receive client information
                Account clientInfo = (Account) ois.readObject();
                System.out.println(clientInfo.getID() + ": " + clientInfo.getUsername());
                notifier.Register(clientInfo.getUsername(), oos);

                //create another thread to receive         
                ClientReceiveThread clientReceiveThread = new ClientReceiveThread(ois, notifier, textEditor, clientInfo.getUsername());

            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(WorkingServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

}
