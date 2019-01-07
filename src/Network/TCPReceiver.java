package Network;

import Message.Message;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TCPReceiver implements Runnable{
	
	private Socket sock;
       
	
	public TCPReceiver(Socket sock){
		this.sock = sock;	
	}

	@Override
	public void run() {
            
                ObjectInputStream objectInput;
            while(true){
            try {
                
                if(sock.getInputStream().available()!=0){
                objectInput = new ObjectInputStream(sock.getInputStream());
                Object object =(Message) objectInput.readObject();
                
                System.out.println(object);
                }
		
            } catch (IOException ex) {
                Logger.getLogger(TCPReceiver.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TCPReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
               	 
	}
	
        }

}
