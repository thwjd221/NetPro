package Client;

import java.awt.Point;
import java.io.*;
import java.net.Socket;
import java.util.Vector;

import Server.User_info;

class ClientThread extends Thread{
	Socket client;
    String myname;
	Room room;
	
	ObjectOutputStream oos;
	ObjectInputStream ois;
	
	public ClientThread(Socket client) {
		this.client = client;
		try {
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    void login(){
        Login lg = new Login();
        lg.setVisible(true);     

        while(true){
            if(!lg.isDisplayable()){    //아이디 입력 성공, 로그인 창 꺼짐
                myname = lg.name();
                try {
					oos.writeObject(myname);
					oos.flush();
					oos.reset();
					room = new Room(client, myname, oos);
					room.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                break;
            }
        }
    }

	@SuppressWarnings("deprecation")
	public void run() {
        login();
		boolean flag = false;
		String str = null;
		BufferedReader br = null;
		
		room.setDraw(false); //채팅 금지
		room.setChat(false);

		for(int i = 0; i < 4; i++) {
			/* 2.
			 * 현재 그리는 유저 이름 수신
			 * */			
			try {
				room.Draw_panel.clear();
				str = (String)ois.readObject();
				
				if(myname.equals(str)) {
					flag = true;
				}
				else {
					flag = false;
				}
			} catch (ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
	        room.Append_Room_chat("[" + str + "]" + " 님이 그림을 그립니다.");
	        	  
			/* 3.
			 * 주제 수신
			 * */
			if(flag) {
	        	room.setDraw(true);
	            try {
	            	str = (String)ois.readObject();
	    			room.subject(str);
	    		} catch (IOException | ClassNotFoundException e) {
	    			e.printStackTrace();
	    		}
	        }
			/* 4.
			 * 그림 수신
			 * */
				try {
					Vector<Point> draw = (Vector<Point>) ois.readObject();
					room.Draw_panel.recvDraw(draw);
					room.Append_Room_chat("그림 수신 완료");
					room.setChat(true);
				} catch(IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			
				
			/* 5.
			 * 정답 수신
			 * */	
			Thread room_thread = new Thread(room);	
			room_thread.start();
	        try {
	        	while(true) {
	        		str = (String)ois.readObject();
	        		room.Append_Room_chat(str);
					if(str.equals("correct!")) {
						room.subject("");
						break;
					}
	        	}

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
	        
			room.setDraw(false); //채팅 금지
			room.setChat(false);
		}
	}
}

public class Client {
	static int mem = 0;
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
