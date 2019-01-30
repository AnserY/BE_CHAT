package Network;

import Message.Message;
import Message.MsgTxt;
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


public class TCPReceiver implements Runnable, Subject{
	
	private Socket sock;
        public Object object;
        private Observer observers;
   
	
	public TCPReceiver(Socket sock){
		this.sock = sock;	
	}

        
      /*  public Object getObject(){
            return this.object;
        }
        */
        
        
	@Override
	public void run() {
            
                ObjectInputStream objectInput;
            while(true){
            try {
                
                if(sock.getInputStream().available()!=0){
                objectInput = new ObjectInputStream(sock.getInputStream());
                object =(Message) objectInput.readObject();
                alert();
               
                }
		
            } catch (IOException ex) {
                Logger.getLogger(TCPReceiver.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TCPReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
               	 
	}
	
        }

    @Override
    public void attach(Observer o) {
            this.observers=o;
    }

    @Override
    public void deattach(Observer o) {
            this.observers=null;
    }

    @Override
    public void alert() {
            this.observers.update(this);
    }

}
