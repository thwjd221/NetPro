import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
class ServerThread extends Thread{
	Socket server;
	String[] subject = new String[] {"cat","school","sandwich","friend"};
	int i = 0;
	//string -> byte 변환
	byte[] transstr = subject[i].getBytes();
	
	public ServerThread (Socket server) {
		this.server = server;
	}
	
	public void run() {
		//주제 전달
		Socket givesub = (Socket) Server.total_socket.get(i);
		try {
			givesub.getOutputStream().write(transstr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ObjectInputStream ois = new ObjectInputStream(givesub.getInputStream());
			//Image img =  (Image)ois.readObject();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class Server {
	static ArrayList total_socket = new ArrayList();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket ss;
		try {
			ss = new ServerSocket(8888);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true) {
			Socket server;
			try {
				server = ss.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			total_socket.add(server);
			//new ServerThread(server).start();
		}

	}

}
