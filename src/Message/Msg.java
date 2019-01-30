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
    public String source;
    
    public Msg(String Text,String source) {
        this.Text = Text;
        this.source=source;
    }
    @Override
    public String toString(){
        return Text;
    }
    
   
    
}
