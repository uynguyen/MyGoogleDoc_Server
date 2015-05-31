/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import Bus.MyBus;
import Pojo.Account;
import Pojo.Document;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThanhTung
 */
public class LogInThread implements Runnable {

    Thread t = null;
    Socket client;

    public LogInThread(Socket client) {
        this.client = client;
        t = new Thread(this);
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = client.getInputStream();
            outputStream = client.getOutputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

            //Receive client username and password for checking
            String clientUserName = bufferedReader.readLine();
            String clientPassword = bufferedReader.readLine();

            //Check login information, null == fail
            Account result = MyBus.checkLoginInfo(clientUserName, clientPassword);

            if (result == null) {
                //Send fail signal back to client
                bufferedWriter.write("fail");
                bufferedWriter.newLine();
                bufferedWriter.flush();

                //Close streams
                bufferedReader.close();
                bufferedWriter.close();
                inputStream.close();
                outputStream.close();
            } else {
                //Object stream for sending Account and Document
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

                //Client list of document
                List<Document> documentList = MyBus.getListDocument(result.getID());
                int listSize = documentList.size();

                //Send pass signal back to client
                bufferedWriter.write("pass");
                bufferedWriter.newLine();

                //Send logined client Account
                objectOutputStream.writeObject(result);

                //Send list size
                bufferedWriter.flush();
                bufferedWriter.write(listSize);

                //Send DocumentsList
                for (int i = 0; i < listSize; i++) {
                    objectOutputStream.writeObject(documentList.get(i));
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(LogInThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
