
package Network;


import Controller.loginController;
import Message.DataAgent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import javax.swing.event.EventListenerList;

import Message.Message;
import Message.MsgHello;
import Message.MsgTxt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class UDPReceiver implements Runnable ,Subject {
        
        private ArrayList<Observer> observers;
        public loginController CC;
	private DatagramSocket socket; // This class represents a socket for sending and receiving datagram packets
	private boolean receptionMessage = false;
	private Message messageRecu;
	private Message[] messagewithip;
        
	public UDPReceiver(loginController CC ) throws SocketException {
            this.CC = CC;
            this.socket = new DatagramSocket(1234);
            this.observers = new  ArrayList<Observer>();
	    this.messagewithip = new Message[10];
	}
	
	public UDPReceiver(DatagramSocket sock) {
		this.socket = sock;
		this.messagewithip = new Message[10];
	}

        
        
	public boolean isReceptionMessage() {
		return receptionMessage;
	}

	public void setReceptionMessage(boolean receptionMessage) {
		this.receptionMessage = receptionMessage;
	}

	public Message getMessageRecu() {
		this.receptionMessage = false;
		return this.messageRecu;
	}

	public Message getMessagewithip(int index) {
		return messagewithip[index];
	}
	
	public void setMessagewithip(int index, Message message) {
		messagewithip[index]=message;
	}
	
	public void interruption(){
		Thread.currentThread().interrupt();
	}

        	
	public DatagramSocket getSocket(){
		return this.socket;
	}
	
	public void closeSocket(){
		System.out.println("Closing socket from port "+this.socket.getLocalPort());
		socket.close();
	}
	
	protected void finalize() throws Throwable
    { 
		try{
			System.out.println("Closing socket from port "+this.socket.getLocalPort());
			socket.close();
		}
		finally{
			super.finalize();
		}
    }
     
        
       private boolean checkLastConnected(ArrayList<DataAgent> connectedList){
      
           Collections.sort(connectedList,new Comparator<DataAgent>() {
            @Override
            public int compare(DataAgent o1, DataAgent o2) {
                 return o1.timeConnexion.after(o2.timeConnexion) ? 1 : -1;
            }
        });
           //System.out.print(connectedList);
            if(connectedList.get(0).pseudo.equals(this.CC.me.pseudo))
            return true;
            else
            return false;
        }


	@Override
	public void run() {
                byte[] buf = new byte[8192];
		Message message=null;
		InetAddress address = null;
		int index = 0; 
		//index of message in the MessageReceived array
		try {
			DatagramPacket messageReceived = new DatagramPacket(buf, buf.length);
			while (!Thread.currentThread().isInterrupted()) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
				//Put the socket in reception mode
				try{
					this.socket.receive(messageReceived);
				}
				catch (IOException e1) {
					return;
				}
				System.out.println("J'ai reçu un message de "+ messageReceived.getSocketAddress());
				try{
					//Deserialize the object and create a Message object from the bytes read
					ByteArrayInputStream bais = new ByteArrayInputStream(messageReceived.getData());
					ObjectInputStream ois =  new ObjectInputStream(bais) ;
					address = messageReceived.getAddress();
					
                                        try {
						message = (Message) ois.readObject();
                                                
                                         if(!messageReceived.getAddress().equals(this.CC.me.myIp)){  
                                           
                                                                                           
                                             
                                              if(message instanceof MsgHello){
                                             
                                                 System.out.println("oui");           
                                                 if(checkLastConnected(this.CC.me.connectedList)){
                                                   
                                                    this.CC.sendList(((MsgHello)message).dataAgent.myIp);
                                                }      
                                                   this.CC.me.connectedList.addAll(((MsgHello)message).dataAgent.connectedList);
                                                   this.alert();
                                                   
                                               }else{
                                                  
                                                   System.out.println("non");   
                                                  
                                                    this.CC.me.connectedList.addAll(((MsgTxt)message).connectedList);
                                                    this.alert();
                                               }
                                              
                                                
                                                
                                             
                                              }
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				this.messageRecu=message;
				this.messagewithip[index] = this.messageRecu;
				index = (index+1)%10;
				this.setReceptionMessage(true);
				//this.newMessageReceived(address);
				System.out.println(this.CC.me.connectedList);
			}
		}
		finally {
			//this.socket.close();
			//Already called by closeSocket() method
		}

	}

    @Override
    public void attach(Observer o) {
            this.observers.add(o);
    }

    @Override
    public void deattach(Observer o) {
            this.observers.remove(o);
    }

    @Override
    public void alert() {
            for (int i = 0; i < observers.size(); i++) {
                observers.get(i).update(this);
        }
    }
}
