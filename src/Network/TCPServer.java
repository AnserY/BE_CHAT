package Network;

import Message.DataAgent;
import View.viewConversationChat;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;


public class TCPServer extends Thread implements Observer{
	
    
        
        private TCPReceiver TCPReceiver ;
	private ServerSocket sockserv = null;
        public  viewConversationChat view;
	private Dictionary<InetAddress,Socket> listSock =null;
        public ArrayList<DataAgent> connectedList=null;
        
        // constructeur
	public TCPServer() {
		try {
			this.sockserv = new ServerSocket(8043);
		} catch (IOException e) {
			System.err.println("ServerSocket couldn't be created");
			e.printStackTrace();
		}
		this.listSock = new Hashtable<InetAddress,Socket>();
		
	}
        public void setView(viewConversationChat view){
            this.view=view;
        }

	@Override
	public void run() {
                while(true){
			try {
                                Socket sock = this.sockserv.accept();
                                this.TCPReceiver = new TCPReceiver(sock);
                                if(this.view == null){
                                    this.view = new viewConversationChat();
                                    for(DataAgent list: connectedList){
                                        if( list.myIp.equals(sock.getInetAddress()) ){
                                            this.view.setData(list,this);
                                        }
                                    }
                                    view.setVisible(true);
                                    this.TCPReceiver.attach(view);    
                                }else{
                                    this.TCPReceiver.attach(view);     
                                }
                                Thread t = new Thread(TCPReceiver);
                                t.start();
                        } catch (IOException e) {
				System.err.println("Client socket couldn't be created");
				e.printStackTrace();
			}
                        }
            
        }

    @Override
    public void update(Object o) {
        this.connectedList=((UDPReceiver)o).CC.me.connectedList;
    }
	

}
