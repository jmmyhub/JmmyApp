package com.jmmy.jmmyapp.Utils;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jmmy on 2017/12/15.
 */

public class JmmyUtils {
    private ServerSocket serverSocket = null;
      public void getSocket (){
          try {
              serverSocket = new ServerSocket(1000);
              Socket socket = serverSocket.accept();
              socket.getInputStream();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }

}
