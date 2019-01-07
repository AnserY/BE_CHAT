/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Message.DataAgent;
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
        private UDPSender UDPSender;
        
        
        
        public loginController(String pseudo) throws UnknownHostException{  
           
            this.UDPSender = new UDPSender();
            this.me = new DataAgent(pseudo);
            this.me.connnectedList.add(this.me);
        }
        
        /*
            
        */
        public void sendBrodcast() throws SocketException{
            this.UDPSender.sendDataAgentAll(this.me);
        }               
        
        public void sendList(InetAddress adr) throws SocketException{
            this.UDPSender.sendConnectedList(this.me, adr);
        }
    
}
