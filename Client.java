import java.io.IOException;
import java.net.Socket;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

class ClientThread extends Thread implements ActionListener{
	Socket client;
	
	String msg;
	
	public ClientThread(Socket client) {
		this.client = client;
	}
	
	public void run() {
		try {
			InputStream in = client.getInputStream();
			while(true) {
				byte b[] = new byte[256];
				in.read(b);
				String msg = new String(b);
				System.out.println(msg+"\n");
			}
		}
		catch(Exception e){
			System.out.println("메시지 수신 오류");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
	}
	public void KeyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			try {
				OutputStream out = client.getOutputStream();
				out.write(msg.getBytes());
			}
			catch(Exception e1) {
				System.out.println("메시지 전송 오류");
			}
		}
	}

}

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {							
			Socket socket = new Socket("155.230.57.60",8888);	//소켓 서버 접속
			
			ClientThread t1 = new ClientThread(socket);
			t1.start();
		}
		catch( IOException e) {
			e.printStackTrace();	//예외
		}
	}

}
