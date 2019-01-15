/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Message;

import java.io.Serializable;

/**
 *
 * @author anser
 */
public class MsgHello extends Message implements Serializable {
    
    public DataAgent dataAgent;           ;
    
    
    
    public MsgHello(DataAgent dataAgent){
       this.dataAgent=dataAgent;  
    
    }
    
      
  //@Override
    
    @Override
    public String toString(){
        return this.dataAgent.pseudo+", connected at: "+this.dataAgent.timeConnexion+", ip : "+this.dataAgent.myIp;
    }

    
}
