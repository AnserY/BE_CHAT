package Network;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import Message.*;

public class UDPSender {

    public DatagramSocket socket; // This class represents a socket for sending and receiving datagram packets
    private Message lastMessage;

    /*
         * __Contruct  
     */
    public UDPSender() {
        try {
            this.socket = new DatagramSocket();
        } catch (SocketException e) {
            System.err.println("Socket couldn't be created.");
            e.printStackTrace();
        }
    }

    public UDPSender(DatagramSocket sock) {
        this.socket = sock;
    }

    /*
	 * Method that send a given message in UDP mode
     */
    private void sendMess(Message mes, InetAddress iptosend) {
        this.lastMessage = mes;
        int port = 1234;
        byte[] buf = new byte[2048];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(mes);
            oos.close();
            buf = baos.toByteArray();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        DatagramPacket mestosend = new DatagramPacket(buf, buf.length, iptosend, port);
        try {
            this.socket.send(mestosend);
        } catch (IOException e) {
            System.err.println("Message failed to send");
            e.printStackTrace();
        }
    }

    public void sendCheck(int type, InetAddress iptosend, String usernameSrc, String usernameDest) {
        MsgCheck mes = null;
        switch (type) {
            case 1:
                //Msg_Check
                mes = new MsgCheck(usernameSrc, usernameDest, false);
                break;
            case 2:
                //Check_Ok
                mes = new MsgCheck(usernameSrc, usernameDest, true);
                break;
            default:
                break;
        }
        this.sendMess(mes, iptosend);
    }

    /*
	 * Send Hello in Broadcast
     */
    public void sendHelloAll(DataAgent dataAgent) {
        InetAddress addr = null;
        try {
            addr = InetAddress.getByName("255.255.255.255");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        MsgHello mes = new MsgHello(dataAgent);
        this.sendMess(mes, addr);
    }

    /*
	 * Send Check in broadcast
     */
    public void sendCheckAll(String usernameSrc, String usernameDest) {
        InetAddress addr = null;
        try {
            addr = InetAddress.getByName("255.255.255.255");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        MsgCheck mes = new MsgCheck(usernameSrc, usernameDest, false);
        this.sendMess(mes, addr);
    }

    /*
            Send Dataagent to all
     */
 /*
        public void sendDataAgentAll(Message message) throws SocketException{
            InetAddress addr=null;
            	try {
			addr = InetAddress.getByName("255.255.255.255");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		this.sendMess(message, addr);

           // this.socket.setBroadcast(false);

        }
        
     */
 /*
            send connectedList to agent
     */
    public void sendConnectedList(MsgTxt message, InetAddress adr) throws SocketException {

        this.sendMess(message, adr);
        //  this.socket.setBroadcast(true);
    }

    /*
	 * Send goodbyeMessage in broadcast
     */
    public void sendBye() {
        InetAddress addr = null;
        try {
            addr = InetAddress.getByName("255.255.255.255");
            MsgGoodbye msggoodbye = new MsgGoodbye();
            this.sendMess(msggoodbye, addr);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /*
	 * Send message Text
     */
 /*
	 * Send message File
     */
    public void sendMessageFile(InetAddress iptosend, String usernameSrc, String usernameDest, File file) {
        MsgFile mes = new MsgFile(usernameSrc, usernameDest, file);
        this.sendMess(mes, iptosend);
    }

    /*
	 * Send message FileAll
     */
    public void sendMessageFileAll(String usernameSrc, String usernameDest, File file) {
        InetAddress addr = null;
        try {
            addr = InetAddress.getByName("255.255.255.255");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        MsgFile mes = new MsgFile(usernameSrc, usernameDest, file);
        this.sendMess(mes, addr);
    }

    public DatagramSocket getSocket() {
        return this.socket;
    }

    public void closeSocket() {
        System.out.println("Closing socket at port " + this.socket.getLocalPort());
        socket.close();
    }

    public Message getLastMessage() {
        return this.lastMessage;
    }

    protected void finalize() throws Throwable {
        try {
            System.out.println("Closing socket at port " + this.socket.getLocalPort());
            socket.close();
        } finally {
            super.finalize();
        }
    }

}
