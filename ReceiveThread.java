import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiveThread extends Thread{
	Socket socket = null;
	
	public ReceiveThread(Socket socket) {
		this.socket = socket;
	}

	public void start() {	//���Ž����� ����
		try{
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
