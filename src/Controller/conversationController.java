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
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class conversationController {
    
            public String BufferMsg [] = new String[10];
            int Indexin = 0;

			 
 public void startServer() throws IOException {
     
      ServerSocket  ss = new ServerSocket(60010);

     Thread T1 = new Thread() {
	           
                    @Override
	            public void run() {
	                                  
                        try {
                            Socket s = ss.accept();
                            
                            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(s.getInputStream()));
                            String line = null;
                            while ((line = in.readLine()) != null) {
                                BufferMsg[Indexin]=line;
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(conversationController.class.getName()).log(Level.SEVERE, null, ex);
                        }

	            }
	        };
     T1.start();
     
}
	
		 
	  
public void startSender(InetAddress address, int port , String msg) throws IOException {

Socket s = new Socket(address, port);
        
Thread T2 = new Thread() {

        @Override
        public void run() {
            try {
               
                
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
    };
T2.start();

} 
	      
	      



    
}
