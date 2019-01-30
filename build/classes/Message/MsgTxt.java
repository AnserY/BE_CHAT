/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Message;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author anser
 */
public class MsgTxt extends Message implements Serializable{
    
   public ArrayList<DataAgent> connectedList ;
   
  //Contructor
  public MsgTxt(ArrayList<DataAgent> connectedList){
      
     this.connectedList=connectedList;
      
  }
    
  @Override
    public String toString(){
        return this.connectedList.toString();
    }
}
