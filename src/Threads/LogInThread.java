/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
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

            String clientUserName = bufferedReader.readLine();
            String clientPassword = bufferedReader.readLine();

            boolean result = CheckLogInInfo(clientUserName, clientPassword);

            if (result == false) {
                bufferedWriter.write("fail");
                bufferedWriter.newLine();
                bufferedWriter.flush();

                bufferedReader.close();
                bufferedWriter.close();
                inputStream.close();
                outputStream.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(LogInThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean CheckLogInInfo(String clientUserName, String clientPassword) {
        return false;
    }

}
