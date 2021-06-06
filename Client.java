import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Image;


class ClientThread extends Thread{
	
	Socket client;
	
	public ClientThread(Socket client) {
		this.client = client;
	}
	
	public void run() {
		Image img = null;
		InputStream in = null;
		OutputStream out = null;
		JTextArea jta = new JTextArea(15, 15);
		//주제 전달받기
		try {
			in = client.getInputStream();
			while(true) {
				byte[] b = new byte[256];
				in.read(b);
				String str = new String(b);
				jta.append(str.trim()+"\n");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		//주제 받은 경우 그림 그리기
		//Draw();
		
		//그림 송신
		try {
			ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
			oos.writeObject(img);
		}
		catch(Exception e) {
			System.out.println("이미지 전송 오류");
		}
		
		//그림 수신
		try {
			ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
			img = (Image)ois.readObject();
		}
		catch(IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//정답 전송
		JTextField jtf = new JTextField(15);
		String msg = jtf.getText();
		try {
			out.write(msg.getBytes());
			jtf.setText("");
		}
		catch(Exception e) {
			System.out.println("메시지 전송 오류");
		}
		
	}
	
}

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {			
			Socket socket;
			socket = new Socket("서버 ip",8888);	//소켓 서버 접속
			
			ClientThread t1 = new ClientThread(socket);
			t1.start(); 
		}
		catch( IOException e) {
			e.printStackTrace();	//예외
		}
	}

}
