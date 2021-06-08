package Client;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JTextArea;
import javax.swing.JTextField;

//import Server.Server;

import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;

class ClientThread extends Thread{
	Socket client;
    String myname = null;
    Integer score;
	Room room;
        
	public ClientThread(Socket client) {
		this.client = client;
		this.score = 0;
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
		boolean flag = false;
		byte[] b = new byte[256];
		String str = new String(b);
		//게임 시작전 시작후 구분
		
		//그림 그리는 사람만 주제 받게 flag 설정
		try {
			in = client.getInputStream();
			in.read(b);
			room.subject(str);
			if(in.read() > 0 && str != "correct!" && str != "no!") flag = true;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		//주제 받은 경우 그림 그리기
		if(flag)
		{
			//Draw();
			//그림 송신
			try {
				ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
				oos.writeObject(img);
			}
			catch(Exception e) {
				System.out.println("이미지 전송 오류");
			}
			flag = false;
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
		while(str == "no!") {
			try {
				out = client.getOutputStream();
				out.write(msg.getBytes());
				jtf.setText("");
			}
			catch(Exception e) {
				System.out.println("메시지 전송 오류");
			}
		}
	}
}
public class Client {

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
