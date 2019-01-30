/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Message;

import java.io.File;

/**
 *
 * @author anser
 */
public class MsgFile extends Message{
    
    String usernameSr,usernameDest;
    File file=null;
    
    //Contructor
    public MsgFile(String usernameSrc, String usernameDest,File file){
        
        this.usernameSr=usernameSrc;
        this.usernameDest=usernameDest;
        this.file=file;
        
    }
}
