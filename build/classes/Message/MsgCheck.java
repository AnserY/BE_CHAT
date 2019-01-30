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
public class MsgCheck extends Message{
    
    public String usernameSrc,usernameDest;
    public boolean ack;//alwayse false here
    
    //Constructor 
    public MsgCheck (String usernameSrc,String usernameDest,boolean ack ){
        this.usernameSrc=usernameSrc;
        this.usernameDest=usernameDest; 
        this.ack=ack;
    }
    
}
