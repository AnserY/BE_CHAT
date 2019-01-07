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
public class MsgTxt extends Message implements Serializable{
    
    public String usernameSrc,usernameDest,message;
  
  //Contructor
  public MsgTxt(String usernameSrc,String usernameDest,String message){
      
      this.message=message;
      this.usernameSrc=usernameSrc;
      this.usernameDest=usernameDest;
      
  }
  
  @Override
    public String toString(){
        return this.message;
    }
}
