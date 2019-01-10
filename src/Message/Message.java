/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Message;

import java.net.InetAddress;
import java.util.Date;
import Network.*;

/**
 *
 * @author anser
 * Father Class of the message
 */
public class Message {
    public InetAddress myIp;
    public String pseudo;
    public Date timeConnexion;
    public String message;
    
    public TCPClient Ctcp;
    
    public Message(InetAddress adr, String message){
        this.Ctcp= new TCPClient(adr);
        this.message=message;
        
    }
    
    
    
    
    
}
