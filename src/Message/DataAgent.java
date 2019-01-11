/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Message;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 *
 * @author anser
 */
public class DataAgent extends Message implements Serializable{
    
    public InetAddress myIp;
    public String pseudo;
    public Date timeConnexion;
    public ArrayList<DataAgent> connnectedList = null ;
    
    // expression réguliere pour le format de l'adresse
    private static final Pattern PATTERN = Pattern.compile(
        "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

    
        
    public DataAgent(String pseudo) throws UnknownHostException, SocketException{
        this.pseudo = pseudo;
        this.myIp = InetAddress.getByName(this.getaddressip());
        this.timeConnexion = new Date();
        this.connnectedList = new ArrayList<DataAgent>();
    }
  
    public String getaddressip() throws SocketException{
        // Parcours de toutes les adresses 
        Enumeration e = NetworkInterface.getNetworkInterfaces();
        while(e.hasMoreElements())
        {
        NetworkInterface n = (NetworkInterface) e.nextElement();
        Enumeration ee = n.getInetAddresses();
        while (ee.hasMoreElements())
        {
            InetAddress i = (InetAddress) ee.nextElement();
            if ( PATTERN.matcher(i.getHostAddress()).matches() ){
                return i.getHostAddress();
            }
         
        }
        
        }   
        return("Erreur pas d'interface trouvée");
    }

    
    
    
    @Override
    public String toString(){
        return this.pseudo+", connected at: "+this.timeConnexion+", ip : "+this.myIp;
    }

   
}
