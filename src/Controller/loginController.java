/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Message.DataAgent;
import Message.MsgTxt;
import Network.UDPSender;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author anser
 */
public class loginController {
    
      
        public DataAgent me;
        public UDPSender UDPSender;
        
        
        
        public loginController(String pseudo) throws UnknownHostException, SocketException{  
           
            this.UDPSender = new UDPSender();
            this.me = new DataAgent(pseudo);
            this.me.connectedList.add(this.me);
        }
        
        /*
            
        */
        public void sendBrodcast() throws SocketException{
            this.UDPSender.sendHelloAll(this.me);
        }               
        
        public void sendList(InetAddress adr) throws SocketException{
            this.UDPSender.sendConnectedList(new MsgTxt (this.me.connectedList), adr);
        }
    
}
