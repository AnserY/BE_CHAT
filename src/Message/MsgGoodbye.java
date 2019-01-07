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
public class MsgGoodbye extends Message{
    
    public String usernameSrc,usernameDest;
            
   public MsgGoodbye(String usernameSrc,String usernameDest){
       
       this.usernameSrc=usernameSrc;
       this.usernameDest=usernameDest;
       
   }
    
}
