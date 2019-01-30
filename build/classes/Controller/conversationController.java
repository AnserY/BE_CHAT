/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author anser
 */

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Message.*;
import Network.*;




public class conversationController {
    
    public TCPClient tcpclient;
    public TCPServer tcpserv;
    public DataAgent me ;
    public DataAgent ToSend;
  
  public conversationController(DataAgent me){
       this.tcpserv = new TCPServer();
       this.me=me;
  }

 public void setConversation(DataAgent agent){
     this.ToSend=agent;
     
 }
   
public void sendMessage(Message msg) throws IOException{
    this.tcpclient = new TCPClient(this.ToSend.myIp);
    this.tcpclient.sendMessage(msg);
}
   
  

   //Constructeur
    /*
   public conversationController(){
    
       this.tcpserv = new TcpServer();
       this.tcpreceive= new TcpReceiver();
       
   }

    public conversationController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
   
   */
       
   }
    

    
    
    
    
    
