/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Message;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author anser
 */
public class DataAgent extends Message implements Serializable{
    
    public InetAddress myIp;
    public String pseudo;
    public Date timeConnexion;
    public ArrayList<DataAgent> connnectedList = null ;
        
    public DataAgent(String pseudo) throws UnknownHostException{
        this.pseudo = pseudo;
        this.myIp = InetAddress.getByName("10.32.1.104");
        this.timeConnexion = new Date();
        this.connnectedList = new ArrayList<DataAgent>();
        
    }
    
    
    
    @Override
    public String toString(){
        return this.pseudo+", connected at: "+this.timeConnexion+", ip : "+this.myIp;
    }

   
}
