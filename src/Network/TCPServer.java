package Network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;


public class TCPServer implements Runnable {
	
    
        
	ServerSocket sockserv;
	Dictionary<InetAddress,Socket> listSock ;
        
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

	@Override
	public void run() {
		while(true){
			try {
                                Socket sock = this.sockserv.accept();
				this.listSock.put(sock.getInetAddress(),sock);
                                Thread th = new Thread(new TCPReceiver(sock));
                                th.start();
			} catch (IOException e) {
				System.err.println("Client socket couldn't be created");
				e.printStackTrace();
			}
		}
		
	}
	

}
