/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Message;

import java.net.InetAddress;

/**
 *
 * @author anser
 */
public class MsgExt extends Message{
    public String usernameSrc,usernameDest;
    public byte [] message ;
    
    
    public MsgExt(String usernameSrc,String usernameDest,byte [] message){
        this.usernameSrc=usernameSrc;
        this.usernameDest=usernameDest;
        this.message=message;
        
    }
    
    @Override
    public String toString(){
        return new String(this.message);
    } 
    
    
}
