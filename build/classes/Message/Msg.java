/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Message;

import java.io.Serializable;
import java.net.InetAddress;

/**
 *
 * @author anser
 */
public class Msg extends Message implements Serializable {
 
    public String Text;
    
    public Msg(String Text) {
        this.Text = Text;
    }
    @Override
    public String toString(){
        return Text;
    }
    
   
    
}
