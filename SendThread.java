import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SendThread extends Thread{
	Socket socket = null;
	Scanner scnner = new Scanner(System.in);
	
	public SendThread(Socket socket) {
		this.socket = socket;
	}

	public void start() {	//송신스레드 동작
		try{
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
