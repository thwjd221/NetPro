package Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Server.User_info;

import java.awt.Graphics;
import java.awt.Image;

class ClientThread extends Thread{
	Socket client;
    String myname = null;
	Room room;
        
	public ClientThread(Socket client) {
		this.client = client;
	}
        
    void login(){
        Login lg = new Login();
        lg.setVisible(true);     

        while(true){
            if(!lg.isDisplayable()){    //아이디 입력 성공, 로그인 창 꺼짐
                myname = lg.name();
                try {
                    PrintStream ps;
                    ps = new PrintStream(client.getOutputStream());
                    ps.println(myname); //서버한테 name 보내줌
                    ps.flush();
                    room = new Room(client, myname);
                    room.setVisible(true);
                } catch (IOException ex) {
        
                }
                break;
            }
        }
    }

	public void run() {
        login();
        
        Image img = null;
		InputStream in = null;
		OutputStream out = null;
		
        //4명 접속할 때까지 기다림
		room.TextField_mychat.setEnabled(false);	//채팅 금지
		
        try {
			in = client.getInputStream();
			byte[] b = new byte[256];
			in.read(b);
			String str = new String(b);
			room.Append_Room_chat(str);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
		//for( i = 0; i < 4; i++) {
			//그림 그리는 사람만 주제 받게 flag 설정
			room.TextField_mychat.setEnabled(true);
			
			//try {
				//in = client.getInputStream();
				//byte[] b = new byte[256];
				//in.read(b);
				//String str = new String(b);
				//room.subject(str);
				//주제 받은 경우 그림 그리기
				room.Draw_panel.setEnabled(true);
			//} catch (IOException e1) {
				// TODO Auto-generated catch block
			//	e1.printStackTrace();
			//}
			
			//그림 수신
			try {
				ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
				room.Draw_panel = (Draw) ois.readObject();
			}
			catch(IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}

public class Client {
	User_info myinfo;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {			
			Socket socket;
			socket = new Socket("localhost",8888);	//소켓 서버 접속
                        
			ClientThread t1 = new ClientThread(socket);
			t1.start();
		}
		catch( IOException e) {
			e.printStackTrace();	//예외
		}
	}

}
