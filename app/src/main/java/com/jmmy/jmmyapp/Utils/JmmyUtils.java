package com.jmmy.jmmyapp.Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Created by jmmy on 2017/12/15.
 */

public class JmmyUtils {
    private static ServerSocket serverSocket = null;
    private static final String TAG = LogUtils.TAG + "JmmyUtils";
      public static void getSocket (){
          try {
              serverSocket = new ServerSocket(1000);
              Socket socket = serverSocket.accept();
              BufferedReader bufferedReader = new BufferedReader(
                      new InputStreamReader(socket.getInputStream()));
              BufferedWriter bufferedWriter = new BufferedWriter(
                      new OutputStreamWriter(socket.getOutputStream()));
              String incommingMsg = bufferedReader.readLine() + System.getProperty("line.separator");
              LogUtils.i(TAG,""+incommingMsg);
              String outgoingMsg = "goodbye from port " + 1000 + System.getProperty("line.separator");
              bufferedWriter.write(outgoingMsg);
              bufferedWriter.flush();
              socket.close();
          }catch (InterruptedIOException e){
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          }finally {
              if (serverSocket != null){
                  try {
                      serverSocket.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          }
      }
}
