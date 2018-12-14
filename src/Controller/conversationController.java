/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author anser
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;



public class conversationController {
    
        String bufferIn [] = new String[10];
	BufferedWriter bufferOut [] = new   BufferedWriter[10];
	int indexIn = 0;
	int indexOut = 0;
	
	 public static void main(String[] args) throws IOException {
	      
		startServer();
		startSender();
		 
		 
	 }
			 
 public static String [] startServer() {
      String bufferIn [] = new String[10];
	        (new Thread() {
	            @Override
	            public void run() {
	                ServerSocket ss;
	                try {
	                    ss = new ServerSocket(60010);

	                    Socket s = ss.accept();

	                    BufferedReader in = new BufferedReader(
	                            new InputStreamReader(s.getInputStream()));
	                    String line = null;
	                    while ((line = in.readLine()) != null) {
	                        bufferIn[index]= line ;
	                    }
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }).start();
}
	
		 
	  
public static void startSender() {
(new Thread() {
        @Override
        String msg = 
        public void run() {
            try {
                Socket s = new Socket("localhost", 60010);
                BufferedWriter out = new BufferedWriter(
                        new OutputStreamWriter(s.getOutputStream()));

                while (true) {
                    out.write(msg);
                    out.newLine();
                    out.flush();

                    Thread.sleep(200);
                }

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }).start();
} 
	      
	      



    
}
