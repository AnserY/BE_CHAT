/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Message;

import Controller.loginController;

/**
 *
 * @author anser
 */
public class MsgGoodbye extends Message{
    
    public DataAgent me ;
            
   public MsgGoodbye( DataAgent me){
       
       this.me=me ;
       
       
   }
    
}
