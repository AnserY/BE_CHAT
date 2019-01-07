package Network;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import Message.*;


public class TCPClient   {
	
	private Socket sock;
	private InetAddress ipToSend;

	public TCPClient(InetAddress ipToSend) {
		this.sock = new Socket();
		this.ipToSend = ipToSend;
	}

        public void sendMessage (Message mes) {
		try {
			this.sock.connect(new InetSocketAddress(this.ipToSend, 8045));
			byte[] buffer = new byte[1024];
			ObjectOutputStream outToServeur = new ObjectOutputStream(sock.getOutputStream());
                        outToServeur.writeObject(mes);
			
			this.sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
