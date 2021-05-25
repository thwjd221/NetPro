import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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
