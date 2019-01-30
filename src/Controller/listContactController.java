/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Message.DataAgent;
import Network.UDPSender;

/**
 *
 * @author anser
 */
public class listContactController {
    public UDPSender udpsender;
    
    public listContactController(){
        this.udpsender = new UDPSender();
    }
    
    public void sendgoodbye(){
        udpsender.sendBye();
        
    }
    
    
    
}
