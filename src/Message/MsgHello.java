/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Message;

/**
 *
 * @author anser
 */
public class MsgHello extends Message{
    
    public String usernameSrc,usernameDest;           ;
    public boolean ack , connect ;//Switch(type) 
    
    
    public MsgHello(String usernameSrc,String usernameDest,boolean ack , boolean connect){
        this.usernameSrc=usernameSrc;
        this.usernameDest=usernameDest;
        this.ack=ack;
        this.connect=connect;
    }
    
      
  //@Override
    /*public String toString(){
        return this.message;
    }*/
    
    
}
